package controller.products;

import java.io.File;
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
	public ModelAndView acountCFInsert(@RequestParam("ingameImagefile") MultipartFile productVipIngameImage,
			@RequestParam("productImageFile") MultipartFile[] productImage, @ModelAttribute("product") @Valid ProductBO productBO,BindingResult result,
			ModelMap model, HttpServletRequest request, HttpSession session, RedirectAttributes redirAtt) {
		UserBO boSesson = (UserBO) request.getSession().getAttribute("userSession");

		if (boSesson == null) {
			model.addAttribute("user", new UserBO());
			return new ModelAndView("/thong-tin-tai-khoan/them-moi");
		}
		
		ModelAndView mav = null;
		
		if (result.hasErrors()) {
			
			mav = new ModelAndView("redirect:/trang-chu/thong-tin-tai-khoan/them-moi");
		}else {
			uploadImage(request,productVipIngameImage,productImage);
		}
		
		return mav;
	}
	
	// upload
	private static List<Map<String, String>> uploadImage( HttpServletRequest request, MultipartFile productVipIngameImage,MultipartFile[] productImage ){
		
		// Thu muc goc upload
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		File uploadFileDir = new File(uploadRootPath);
		
		// tao thu muc goc neu thu muc chua ton tai
		if(!uploadFileDir.exists()) {
			uploadFileDir.mkdirs();
		}
		
		// tao co check upload
		boolean flag = true;
		// upload productVipIngameImage
		flag = Utils.uploadFile(uploadFileDir, productVipIngameImage);
		
		// check upload file productVipIngameImage
		if(!flag) {
			// throw Exception		
		}
		
		// upload file productImage
		for (MultipartFile productImageFile : productImage) {
			
			// thuc hien upload
			flag = Utils.uploadFile(uploadFileDir, productImageFile);
		}
		
		
		return null;
	}
}
