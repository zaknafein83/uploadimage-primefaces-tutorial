package it.primefaces.tutorial.beans;

import java.io.ByteArrayInputStream;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@Named
@SessionScoped
public class PreviewBean {
    
    private CroppedImage croppedImage;
    
    private UploadedFile originalImageFile;

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }
    
    public UploadedFile getOriginalImageFile() {
        return originalImageFile;
    }
    
    public void handleFileUpload(FileUploadEvent event) {
        this.originalImageFile = null;
        this.croppedImage = null;
        UploadedFile file = event.getFile();
        if(file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
            this.originalImageFile = file;
            System.out.println("ho settato il file");
        }
    }

    public void crop() {
        if(this.croppedImage == null || this.croppedImage.getBytes() == null || this.croppedImage.getBytes().length == 0) {
        	System.out.println("errore nel cropping");
        }
        else {
        	System.out.println("successo nel cropping");
        }
    }
    
    public StreamedContent getImage() {
        
        FacesContext context = FacesContext.getCurrentInstance();

        System.out.println("Passiamo nel getImage");
        StreamedContent result = null;
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE || this.originalImageFile == null
                || this.originalImageFile.getContent() == null || this.originalImageFile.getContent().length == 0) {
        	System.out.println("Passiamo nel getImage - Render Phase con originalImageFile == Null");
            result = new DefaultStreamedContent();
        }
        else {
        	System.out.println("Passiamo nel getImage - Render Phase");
            result = DefaultStreamedContent.builder().contentType(this.originalImageFile.getContentType()).stream(() -> {
                try {
                    return new ByteArrayInputStream(this.originalImageFile.getContent());
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }).build();
        }
        
        return result;
    }

    public StreamedContent getCropped() {
        
        FacesContext context = FacesContext.getCurrentInstance();

        StreamedContent result = null;
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE || this.croppedImage == null
                || this.croppedImage.getBytes() == null || this.croppedImage.getBytes().length == 0) {
            result = new DefaultStreamedContent();
        }
        else {
            result = DefaultStreamedContent.builder().contentType(this.originalImageFile.getContentType()).stream(() -> {
                try {
                    return new ByteArrayInputStream(this.croppedImage.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }).build();
        }
        
        return result;
    }}
