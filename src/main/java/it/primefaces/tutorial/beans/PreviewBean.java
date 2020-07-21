package it.primefaces.tutorial.beans;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

@Named
@ViewScoped
public class PreviewBean {

	private UploadedFile file;
	private byte[] content;

	public void handleFileUpload(FileUploadEvent event) {
		file = event.getFile();
		System.out.println("siamo qui " + file.getFileName());

		setContent(file.getContent());

		
		
		

	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

}
