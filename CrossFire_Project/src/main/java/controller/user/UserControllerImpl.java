package controller.user;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.CommonFinal;
import common.ExceptionID;
import common.LogicException;
import common.utils.MD5Utils;
import common.utils.SendMailUtils;
import common.utils.Utils;
import model.bo.UserBO;
import model.business.user.UserBusiness;
import model.business.user.UserBusinessImpl;

@Controller
public class UserControllerImpl{

	private static final int NUMBER_OF_CHARACTOR = 6;
	private static final Logger logger = Logger.getLogger(UserControllerImpl.class);
	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	private SendMailUtils sendMailUtils = (SendMailUtils) context.getBean("sendMail");

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserFormLoginValidator());
		binder.setValidator(new UserFormSendVeirifyCodeValidator());
		binder.setValidator(new UserFormSendPasswordValidator());
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String LoginForm(ModelMap model) {
		model.addAttribute("user", new UserBO());
		return "login";
	}
	
	// thuc hien login
	@RequestMapping(value = "/LoginThanhCong", method = RequestMethod.POST)
	public ModelAndView UserLogin(@ModelAttribute("user") UserBO userBO, BindingResult result, ModelMap model,
			HttpServletRequest request, RedirectAttributes redirAtt) throws Exception {
		ModelAndView mav = null;

		new UserFormLoginValidator().validate(userBO, result);

		if (result.hasErrors()) {
			String errors = result.getAllErrors().get(0).getDefaultMessage();
			redirAtt.addFlashAttribute("ErrorMesage", errors);
			redirAtt.addFlashAttribute("userName", userBO.getUserName());
			redirAtt.addFlashAttribute("userPassword", userBO.getUserPassword());
			mav = new ModelAndView("redirect:/login?error=true");
		} else {

			UserBusiness userBusiness = new UserBusinessImpl();

			try {
				userBusiness.getUser(userBO.getUserName(), userBO.getUserPassword());
				HttpSession session = request.getSession(true);
				session.setAttribute("userSession", userBO);
				mav = new ModelAndView("redirect:/trang-chu/");
			} catch (LogicException e) {
				Utils utils = new Utils();
				Exception ex = utils.getLogicException(null, e.getErrorCode());
				redirAtt.addFlashAttribute("ErrorMesage", ex.getMessage());
				redirAtt.addFlashAttribute("userName", userBO.getUserName());
				redirAtt.addFlashAttribute("userPassword", userBO.getUserPassword());
				mav = new ModelAndView("redirect:/login?error=true");
			}
		}
		return mav;
	}

	/*
	 * dang xuat
	 * 
	 */
	@RequestMapping(value = "/dang-xuat")
	public ModelAndView UserLogout(@ModelAttribute("user") UserBO userBO, BindingResult result, ModelMap model,
			HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession(true);
		session.setAttribute("user", null);
		session.removeAttribute("user");
		ModelAndView mav = new ModelAndView("redirect:/login");
		return mav;
	}

	/*
	 * Gui ma xac thuc
	 * 
	 */
	@RequestMapping(value = { "/gui-ma-xac-thuc" }, method = RequestMethod.GET)
	public String UserForgotPassword(ModelMap model) {
		model.addAttribute("user", new UserBO());
		return "send-verify-code";
	}

	@RequestMapping(value = { "/xac-thuc" }, method = RequestMethod.GET)
	public ModelAndView UserSendVerifyCode(ModelMap model, HttpServletRequest request, HttpSession session) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
		model.addAttribute("user", new UserBO());
		if (boSesson == null) {
			return new ModelAndView("redirect:/login");
		}

		return new ModelAndView("sendPassword");
	}

	/*
	 * Gui ma xac thuc
	 * 
	 */
	@RequestMapping(value = { "/xac-thuc" }, method = RequestMethod.POST)
	public ModelAndView UserSendVerifyCode(@ModelAttribute("user") UserBO userBO, BindingResult result, ModelMap model,
			HttpServletRequest request, RedirectAttributes redirAtt, HttpSession session) throws Exception {

		ModelAndView mav = null;
		new UserFormSendVeirifyCodeValidator().validate(userBO, result);
		session = request.getSession(true);
		session.setAttribute("userSession", userBO);

		// validate gia tri dau vao.
		if (result.hasErrors()) {
			String errors = result.getAllErrors().get(0).getDefaultMessage();
			redirAtt.addFlashAttribute("ErrorMesage", errors);
			redirAtt.addFlashAttribute("userName", userBO.getUserName());
			redirAtt.addFlashAttribute("userEmail", userBO.getUserEmail());
			mav = new ModelAndView("redirect:/gui-ma-xac-thuc?error=true");
		} else {
			// set Localdate
			Locale.setDefault(Locale.ENGLISH);
			UserBusiness userBusiness = (UserBusiness) new UserBusinessImpl();

			try {

				UserBO userBOQuery = userBusiness.getUserVerify(userBO.getUserName(), userBO.getUserEmail());

				// send mail cap lai ma xac thuc
				String strverifyCode = Utils.randomAlphaNumeric(NUMBER_OF_CHARACTOR);
				String strMailTo = CommonFinal.EMAIL_TO;
				String strMailSubject = "Mã xác nhận tài khoản.";
				String strMailMessage = "Mã xác nhận: " + strverifyCode;

				// thuc hien update data tbluser
				// fommat date
				DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
				// create verify Code Create Date
				LocalDateTime verifyCodeCreateDate = LocalDateTime.now();
				// create verify Code Expried Date
				LocalDateTime verifyCodeExpriedDate = verifyCodeCreateDate.plusMinutes(1);

				// set gia tri cho userBO
				userBOQuery.setUserVerifyCode(MD5Utils.MD5Code(strverifyCode));
				userBOQuery.setUserVerifyCodeCreateDate(dateTimeFormatter.format(verifyCodeCreateDate));
				userBOQuery.setUserVerifyCodeExpriedDate(dateTimeFormatter.format(verifyCodeExpriedDate));
				userBOQuery.setUserUpdateDate(dateTimeFormatter.format(verifyCodeCreateDate));

				// send ma chung thuc
				sendMailUtils.SendMail(strMailTo, strMailSubject, strMailMessage);
				// update ma chung thuc
				userBusiness.updateUser(userBOQuery);
				redirAtt.addFlashAttribute("SuccessMesage", "Gửi mã xác thực thành công.Xin vui lòng xác nhận mail");
				redirAtt.addFlashAttribute("userName", userBO.getUserName());
				redirAtt.addFlashAttribute("userEmail", userBO.getUserEmail());
				// chuyen trang cap lai password
				mav = new ModelAndView("redirect:/xac-thuc");

			} catch (LogicException e) {
				Utils utils = new Utils();
				Exception ex = utils.getLogicException(null, e.getErrorCode());
				redirAtt.addFlashAttribute("ErrorMesage", ex.getMessage());
				redirAtt.addFlashAttribute("userName", userBO.getUserName());
				redirAtt.addFlashAttribute("userEmail", userBO.getUserEmail());
				mav = new ModelAndView("redirect:/gui-ma-xac-thuc?error=true");
			}
		}
		return mav;
	}

	@RequestMapping(value = { "/cap-lai-mat-khau" }, method = RequestMethod.POST)
	public ModelAndView SendPassword(@ModelAttribute("user") UserBO userBO, BindingResult result, ModelMap model,
			HttpServletRequest request, RedirectAttributes redirAtt) throws Exception {

		ModelAndView mav = null;
		new UserFormSendPasswordValidator().validate(userBO, result);
		// validate gia tri dau vao.
		if (result.hasErrors()) {
			String errors = result.getAllErrors().get(0).getDefaultMessage();
			redirAtt.addFlashAttribute("ErrorMesage", errors);
			redirAtt.addFlashAttribute("userVerifyCode", userBO.getUserVerifyCode());
			redirAtt.addFlashAttribute("SuccessMesage", "");
			mav = new ModelAndView("redirect:/xac-thuc?error=true");
		} else {
			// set Localdate
			Locale.setDefault(Locale.ENGLISH);
			UserBusiness userBusiness = (UserBusiness) new UserBusinessImpl();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.US);
			// create verify Code Create Date
			LocalDateTime currentDate = LocalDateTime.now();

			try {
				UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");

				UserBO userBOQuery = userBusiness.getUserVerify(boSesson.getUserName(), boSesson.getUserEmail());
				LocalDateTime verifyCodeExpriedDate = LocalDateTime.parse(userBOQuery.getUserVerifyCodeExpriedDate(),
						dateTimeFormatter);

				if (MD5Utils.MD5Code(userBO.getUserVerifyCode()).equals(userBOQuery.getUserVerifyCode())) {
					if (verifyCodeExpriedDate.isAfter(currentDate)) {

						// send mail cap lai ma xac thuc
						String strpassword = Utils.randomPassword(NUMBER_OF_CHARACTOR);
						String strMailTo = CommonFinal.EMAIL_TO;
						String strMailSubject = "Mật Khẩu cấp lại";
						String strMailMessage = "Mật khẩu : " + strpassword;

						// send ma chung thuc
						sendMailUtils.SendMail(strMailTo, strMailSubject, strMailMessage);
						userBOQuery.setUserPassword(MD5Utils.MD5Code(strpassword));
						userBOQuery.setUserUpdateDate(dateTimeFormatter.format(currentDate));
						// update ma chung thuc
						userBusiness.updateUser(userBOQuery);
						redirAtt.addFlashAttribute("SuccessMesage",
								"Cấp lại mật khẩu thành công.Xin vui lòng xác nhận mail");
						redirAtt.addFlashAttribute("requestParam", "false");
						// chuyen trang cap lai password
						mav = new ModelAndView("redirect:/cap-lai-mat-khau?error=false");

					} else {
						Utils utils = new Utils();
						Exception e = utils.getLogicException(null, ExceptionID.MU0005);
						// thuc hien ghi log
						logger.info("phát sinh Business Exception", e);
						redirAtt.addFlashAttribute("ErrorMesage", e.getMessage());
						redirAtt.addFlashAttribute("SuccessMesage", "");
						redirAtt.addFlashAttribute("requestParam", "true");
						mav = new ModelAndView("redirect:/cap-lai-mat-khau?error=true");
					}
				}

			} catch (LogicException e) {
				Utils utils = new Utils();
				Exception ex = utils.getLogicException(null, e.getErrorCode());
				redirAtt.addFlashAttribute("ErrorMesage", ex.getMessage());
				redirAtt.addFlashAttribute("userName", userBO.getUserName());
				redirAtt.addFlashAttribute("userEmail", userBO.getUserEmail());
				mav = new ModelAndView("redirect:/gui-ma-xac-thuc?error=true");
			}
		}

		return mav;
	}

	@RequestMapping(value = { "/cap-lai-mat-khau" }, method = RequestMethod.GET)
	public ModelAndView sendPasswordSuccess(ModelMap model, HttpServletRequest request, HttpSession session) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("redirect:/login");
		}

		session = request.getSession(true);
		session.setAttribute("requestParam", request.getParameter("error"));

		return new ModelAndView("sendPasswordSuccess");
	}

	@RequestMapping(value = { "/comeback" }, method = RequestMethod.GET)
	public ModelAndView comeback(ModelMap model, HttpServletRequest request, HttpSession session) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
		model.addAttribute("user", new UserBO());
		if (boSesson == null) {
			return new ModelAndView("redirect:/login");
		}

		return new ModelAndView("sendPasswordSuccess");
	}

	@RequestMapping(value = { "/comeback" }, method = RequestMethod.POST)
	public ModelAndView comeback(@ModelAttribute("user") UserBO userBO, BindingResult result, ModelMap model,
			HttpServletRequest request, RedirectAttributes redirAtt, HttpSession session) throws Exception {
		ModelAndView mav = null;
		String reqParam = (String) request.getSession().getAttribute("requestParam");
		if (reqParam.equals("false")) {
			session.removeAttribute("userSession");
			mav = new ModelAndView("redirect:/login");
		} else if (reqParam.equals("true")) {
			UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
			redirAtt.addFlashAttribute("userName", boSesson.getUserName());
			redirAtt.addFlashAttribute("userEmail", boSesson.getUserEmail());
			mav = new ModelAndView("redirect:/gui-ma-xac-thuc");
		}
		session.removeAttribute("requestParam");

		return mav;
	}

}
