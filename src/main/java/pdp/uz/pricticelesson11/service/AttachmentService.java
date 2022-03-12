package pdp.uz.pricticelesson11.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import pdp.uz.pricticelesson11.entity.Attachment;
import pdp.uz.pricticelesson11.entity.AttachmentContent;
import pdp.uz.pricticelesson11.payload.ApiResponse;
import pdp.uz.pricticelesson11.repository.AttachmentContentRepository;
import pdp.uz.pricticelesson11.repository.AttachmentRepository;

import java.util.Iterator;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @SneakyThrows
    public ApiResponse uploadFile(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());

        Attachment attachment=new Attachment();
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContent_type(file.getContentType());
        Attachment attachmentSaved = attachmentRepository.save(attachment);

        AttachmentContent attachmentContent=new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(attachmentSaved);
        attachmentContentRepository.save(attachmentContent);
        return new ApiResponse("File saved", true,attachmentSaved.getId());


    }


}
