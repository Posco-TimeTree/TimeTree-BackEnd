package pack.springjpa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pack.springjpa1.common.domain.CanvasStateRequest;
import pack.springjpa1.data.dto.TreeDTO;
import pack.springjpa1.data.service.TreeService;
import java.util.Base64;

@CrossOrigin(origins = {"http://175.45.202.192:3000", "http://localhost:3000"} ,allowCredentials = "true")
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class CanvasController {
    private final TreeService treeService;

    public CanvasController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping("/save_image")
    public String saveCanvasState(@RequestBody CanvasStateRequest request) {
        int userId = request.getUserId();
        String canvasState = request.getCanvasState();
        Long convertedUserId = (long) userId;
        byte[] existingTree = treeService.getImageDataByUserId(convertedUserId);
        String base64Data = canvasState.substring(canvasState.indexOf(";base64,") + 8);

        if (existingTree != null) {
            byte[] imageData = convertCanvasStateToBytes(base64Data);
            treeService.updateTreeData(convertedUserId, imageData);

        } else {

            byte[] imageData = convertCanvasStateToBytes(base64Data);
            TreeDTO treeDTO = new TreeDTO();
            treeDTO.setUserId(convertedUserId);
            treeDTO.setImageData(imageData);
            treeService.createTree(treeDTO);
        }

        return "Canvas state saved successfully";
    }

    private byte[] convertCanvasStateToBytes(String canvasState) {
        byte[] canvasStateBytes = Base64.getDecoder().decode(canvasState);
        return canvasStateBytes;
    }

    @GetMapping("/usertree/{userId}")
    public String getUserTree(@PathVariable int userId) {
        Long convertedUserId = (long) userId;
        System.out.println("Blob데이터 가져오기  ");
        byte[] blobData = treeService.getImageDataByUserId(1L);
        if (blobData != null) {
            // Blob 데이터를 Base64로 인코딩하여 문자열로 변환합니다.
            String encodedData = Base64.getEncoder().encodeToString(blobData);
            System.out.println("Blob데이터 가져오기 ");
            String imageDataWithPrefix = "data:image/png;base64," + encodedData;
            // 변환된 문자열을 반환합니다.
            return imageDataWithPrefix;


        } else {
            System.out.println("blob data엔 데이터가 없습닏다.");
            // 해당 userId에 대한 데이터가 없을 경우 예외 처리 또는 기본값 반환 등을 수행합니다.
            return ""; // 예시로 빈 문자열을 반환합니다.
        }
    }



}
