package it.primefaces.tutorial.beans;

import java.util.Base64;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

@ManagedBean
@RequestScoped
public class PreviewBean {

	private UploadedFile file;
	private byte[] image;

	public void handleFileUpload(FileUploadEvent event) {
		setFile(event.getFile());
		System.out.println("File Name " + getFile().getFileName());

		setImage(getFile().getContent());
	}

	public String getImageContentsAsBase64() {
	    return Base64.getEncoder().encodeToString(image);
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
