package common.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import common.ExceptionID;
import common.LogicException;

public class Utils {

	private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
	private static final String alphaUpperCase = alpha.toUpperCase();
	private static final String digits = "0123456789";
	private static final String specials = "~=+%^*/()[]{}/!@#$?|";
	private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
	private static final String ALL = alpha + alphaUpperCase + digits + specials;
	private static Random generator = new Random();
	private static final Logger logger = Logger.getLogger(Utils.class);

	public static boolean isValidEmail(String email) {

		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	/** * Random string with a-zA-Z0-9, not included special characters */
	public static String randomAlphaNumeric(int numberOfCharactor) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numberOfCharactor; i++) {
			int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
			char ch = ALPHA_NUMERIC.charAt(number);
			sb.append(ch);
		}
		return sb.toString();
	}

	/** * Random string password with at least 1 digit and 1 special character */
	public static String randomPassword(int numberOfCharactor) {
		List<String> result = new ArrayList<String>();
		Consumer<String> appendChar = s -> {
			int number = randomNumber(0, s.length() - 1);
			result.add("" + s.charAt(number));
		};
		appendChar.accept(digits);
		appendChar.accept(specials);
		while (result.size() < numberOfCharactor) {
			appendChar.accept(ALL);
		}
		Collections.shuffle(result, generator);
		return String.join("", result);
	}

	private static int randomNumber(int min, int max) {
		return generator.nextInt((max - min) + 1) + min;
	}

	/*
	 * get Exception message
	 * 
	 * @param : Exception e
	 * 
	 * @param : String exceptionID
	 * 
	 * return Exception logicException
	 */
	public Exception getLogicException(Exception e, String exceptionID) {
		Exception logicException;

		// khoi tao properties
		Properties properties = new Properties();
		String messageLog = "";
		InputStream inStream = null;
		try {
			ClassLoader cLoader = this.getClass().getClassLoader();
			// doc file exceptionMessage.properties
			inStream = cLoader.getResourceAsStream("exceptionMessage.properties");
			// load file exceptionMessage.properties lay data
			properties.load(inStream);
			// truong hop exceptionID khac blank.
			if (!exceptionID.isEmpty()) {
				messageLog = properties.getProperty(exceptionID);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if (e == null) {
			e = new Exception("");
		}

		// tao noi dung message.
		if (e.getMessage().isEmpty()) {
			logicException = new Exception(exceptionID + " " + messageLog + "\n");
		} else {
			logicException = new Exception(exceptionID + " " + messageLog + "\n" + e.getMessage());
		}

		return logicException;
	}

	// upload image
	public static String uploadFile(File uploadFileDir, MultipartFile file) throws LogicException {

		// get file name.
		String nameFile = file.getOriginalFilename();
		String pathfile = "";
		if (nameFile != null && nameFile.length() > 0) {
			pathfile = uploadFileDir.getAbsolutePath() + File.separator + nameFile;
			File serverFileIngame = new File(pathfile);

			// luong ghi du lieu vao file tren server
			try {
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFileIngame));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				Utils utils = new Utils();
				Exception ex = utils.getLogicException(null, ExceptionID.SH0001);
				// thực hiện ghi log.
				logger.error("upload file không thành công", ex);

				throw new LogicException(ExceptionID.MSH0001);
			}
		}

		return nameFile;
	}

}
