package it.primefaces.tutorial.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

@ManagedBean(name = "upload")
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
		//    <p:fileUpload value="#{upload.file}" mode="simple" skinSimple="true"/>

		if (file != null) {
			System.out.println("Yes, now you can save it on DB");
			FacesMessage message = new FacesMessage("Successful", file.getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} else {
			System.out.println("We have a trouble");
		}
	}
	
	public void handleFileUpload(FileUploadEvent e){
		// Get uploaded file from the FileUploadEvent
		this.file = e.getFile();
		// Print out the information of the file
		System.out.println("Uploaded File Name Is :: "+file.getFileName()+" :: Uploaded File Size :: "+file.getSize());
	}


}
/**


    <p:fileUpload value="#{upload.file}" mode="simple" skinSimple="true"/>
    <br />
    
    <p:commandButton value="Submit" ajax="false" action="#{upload.upload}"  />

*/