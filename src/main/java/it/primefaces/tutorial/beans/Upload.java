package it.primefaces.tutorial.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

@Named(value = "upload")
@RequestScoped
public class Upload {

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		if (file != null) {
			System.out.println("entro nell'if");
			FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			System.out.println("null--");
		}
	}
	
	public void handleFileUpload(FileUploadEvent e){
		// Get uploaded file from the FileUploadEvent
		this.file = e.getFile();
		// Print out the information of the file
		System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
	}


}
