package controller.products;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.LogicException;
import model.bo.ProductBO;
import model.bo.UserBO;
import model.business.products.ProductBusiness;
import model.business.products.ProductBusinessImpl;

@Controller
@RequestMapping(value = "/trang-chu")
public class ProductControllerImpl {
	//private static final Logger logger = Logger.getLogger(UserControllerImpl.class);
//	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//	private ProductBusiness productBusiness = (ProductBusiness) context.getBean("productbusiness");

	@GetMapping(value = { "/" })
	public ModelAndView homePage(ModelMap model, HttpServletRequest request, HttpSession session) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
		model.addAttribute("user", new UserBO());
		if (boSesson == null) {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("index");
	}

	@GetMapping(value = { "/thong-tin-tai-khoan" })
	public ModelAndView acountCFInfor(ModelMap model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirAtt) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("redirect:/login");
		}

		ProductBusiness productBusiness = new ProductBusinessImpl();
		try {
			List<ProductBO> listProductBO = productBusiness.getAllProduct();
			model.addAttribute("productData", listProductBO);
		} catch (LogicException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("thong-tin-tai-khoan", model);
	}

	@GetMapping(value = { "/thong-tin-tai-khoan/them-moi" })
	public ModelAndView acountCFInsertHandler(ModelMap model, HttpServletRequest request, HttpSession session,
			RedirectAttributes redirAtt) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("redirect:/login");
		}

		ProductBO productBO = new ProductBO();

		model.addAttribute("product", productBO);
		return new ModelAndView("them-moi");
	}

	@PostMapping(value = { "/thong-tin-tai-khoan/them-moi" })
	public ModelAndView acountCFInsert(@RequestParam("productVipIngameImage") MultipartFile productVipIngameImage ,ModelMap model, HttpServletRequest request,
			HttpSession session, RedirectAttributes redirAtt) { 
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("redirect:/login");
		}

		return new ModelAndView("them-moi");
	}

}
