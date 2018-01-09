package com.ace.training.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ace.training.exceptions.DaoException;
import com.ace.training.service.StudentJsonService;
import com.ace.training.service.UploadToDbService;

@Controller
public class MainController {

	private static Logger LOG = LoggerFactory.getLogger(MainController.class);
	
	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = System.getProperty("user.dir")
			+ File.separator + "tmp";

	private static String JSON_EXTENTION = ".JSON";

	@Autowired
	private UploadToDbService uploadToDbService;
	
	@Autowired
	private StudentJsonService studentJsonService;
	
	@RequestMapping("/")
	public String showWelcomePage() {
		return "upload";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public ModelAndView singleFileUpload(@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		
		File directory = new File(UPLOADED_FOLDER);
		if (!directory.exists()) {
			directory.mkdir();
		}

		// Delete all the files in the directory before uploading a new Xml.
		File[] files = directory.listFiles();
		if (files != null) {
			for (File f : files) {
				f.delete();
			}
		}
		
		ModelAndView model = new ModelAndView("display");
		
		try {
			
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + File.separator
					+ file.getOriginalFilename());
			Files.write(path, bytes);

			uploadToDbService.parseXml(UPLOADED_FOLDER + File.separator
					+ file.getOriginalFilename());
			
			//create the student report
			studentJsonService.createStudentJson();
			
			model.addObject("msg","Files have been uploaded successfully!");		
			return model;		
			
		} catch (IOException e) {
			LOG.error("Xml file couldn't be saved to local " + e);
			model.addObject("msg", "File can't be uploaded now. Please try later");
		} catch (JAXBException e) {
			LOG.error("Xml file not in the correct format " + e);
			model.addObject("msg", "XML file not in correct format. Please check");
		} catch (DaoException e) {
			model.addObject("msg", e.getMessage());
		}
		return model;

	}

	@RequestMapping(value = "/viewReport", method = RequestMethod.GET)
	public String viewReport() {
		return "viewReport";
	}

	@RequestMapping(value = "/studentReport", method = RequestMethod.POST)
	public ModelAndView studentReport(@RequestParam("id") String id) {
		File directory = new File(UPLOADED_FOLDER);
		File[] files = directory.listFiles();
		ModelAndView model = new ModelAndView("display");
		for (File file : files) {
			Path p = Paths.get(file.getAbsolutePath());
			if (p.getFileName().toString()
					.equalsIgnoreCase(id + JSON_EXTENTION)) {
				try {
					byte[] reportData = Files.readAllBytes(p);
					String data = new String(reportData);
					model.addObject("msg", data);
					return model;
				} catch (Exception e) {
					model = new ModelAndView("error");
					LOG.error("Student Report not genrated" + e);
					model.addObject("msg", "Student Report not genrated");
				}

			}

		}
		model.addObject("msg", "Report not available for the given Student Id. Please check later.");
		return model;
	}

}
