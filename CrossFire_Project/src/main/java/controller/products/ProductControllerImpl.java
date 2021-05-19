package controller.products;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import common.LogicException;
import common.utils.Utils;
import model.bo.ProductBO;
import model.bo.UserBO;
import model.business.products.ProductBusiness;
import model.business.products.ProductBusinessImpl;

@Controller
@RequestMapping(value = "/trang-chu")
public class ProductControllerImpl {
	// private static final Logger logger =
	// Logger.getLogger(UserControllerImpl.class);
//	private ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//	private ProductBusiness productBusiness = (ProductBusiness) context.getBean("productbusiness");
	@Autowired
	private ProductFormValidator productFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(productFormValidator);
	}

	@GetMapping(value = { "/" })
	public ModelAndView homePage(ModelMap model, HttpServletRequest request, HttpSession session) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");
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
			Utils utils = new Utils();
			Exception ex = utils.getLogicException(null, e.getErrorCode());
			redirAtt.addFlashAttribute("ErrorMesage", ex.getMessage());
			return new ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan");
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
	public ModelAndView acountCFInsert(@RequestParam("ingameImagefile") MultipartFile productVipIngameImage,
			@RequestParam("productImageFile") MultipartFile[] productImage,
			@ModelAttribute("product") @Valid ProductBO productBO, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpSession session, RedirectAttributes redirAtt) {

		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");

		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("redirect:/login");
		}


		if (result.hasErrors()) {

			model.addAttribute("product", productBO);
			return new ModelAndView("them-moi");
		} else {
			try {

				// get current date time
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String currentDateTime = now.format(dateTimeFormat);

				// get name file
				List<Map<String, List<String>>> dataName = new ArrayList<Map<String, List<String>>>();
				dataName = uploadImage(request, productVipIngameImage, productImage);

				// set value Product
				productBO.setProductVipIngameImage(dataName.get(0).get("ListFileNameVipIngameImage").get(0));
				productBO.setProductCreateDate(currentDateTime);
				productBO.setProductUserAdd(boSesson.getUserName());

				ProductBusiness productBusiness = new ProductBusinessImpl();

				List<String> listProductImage = new ArrayList<String>();
				listProductImage = dataName.get(0).get("listProductImage");
				int imageId = 0;
				for (int i = 0; i < listProductImage.size(); i++) {
					imageId++;
					productBO.setProductImageId(imageId);
					productBO.setProductImage(listProductImage.get(i));
					productBusiness.insertProduct(productBO);
				}
				
				redirAtt.addFlashAttribute("SuccessMesage", "Đăng ký thông tin tài khoản CF thành công.");
				
			} catch (LogicException e) {
				
				Utils utils = new Utils();
				Exception ex = utils.getLogicException(null, e.getErrorCode());
				redirAtt.addFlashAttribute("ErrorMesage", ex.getMessage());
				redirAtt.addFlashAttribute("SuccessMesage", "");
				return new ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan/them-moi");
			}
		}

		return new  ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan/them-moi");
	}

	// upload
	private static List<Map<String, List<String>>> uploadImage(HttpServletRequest request,
			MultipartFile productVipIngameImage, MultipartFile[] productImage) throws LogicException {

		List<Map<String, List<String>>> dataName = new ArrayList<Map<String, List<String>>>();
		Map<String, List<String>> mapValue = new HashMap<String, List<String>>();

		// Thu muc goc upload
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		File uploadFileDir = new File(uploadRootPath);

		// tao thu muc goc neu thu muc chua ton tai
		if (!uploadFileDir.exists()) {
			uploadFileDir.mkdirs();
		}

		List<String> listFileNameVipIngameImage = new ArrayList<String>();
		// upload productVipIngameImage
		String fileNameVipIngameImage = Utils.uploadFile(uploadFileDir, productVipIngameImage);
		listFileNameVipIngameImage.add(fileNameVipIngameImage);
		mapValue.put("ListFileNameVipIngameImage", listFileNameVipIngameImage);

		// upload file productImage

		List<String> listProductImage = new ArrayList<String>();
		for (MultipartFile productImageFile : productImage) {

			// thuc hien upload
			String fileNameUpdload = Utils.uploadFile(uploadFileDir, productImageFile);
			listProductImage.add(fileNameUpdload);
		}
		mapValue.put("listProductImage", listProductImage);

		dataName.add(mapValue);

		return dataName;
	}
}
