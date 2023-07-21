package pack.springjpa1.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pack.springjpa1.data.dao.TreeDAO;
import pack.springjpa1.data.dto.TreeDTO;
import pack.springjpa1.data.entity.TreeEntity;

@Service
public class TreeService {
    private final TreeDAO treeDAO;

    @Autowired
    public TreeService(TreeDAO treeDAO) {
        this.treeDAO = treeDAO;
    }

    public void createTree(TreeDTO treeDTO) {
        TreeEntity treeEntity = new TreeEntity();
        // Map DTO fields to Entity fields
        treeEntity.setUserId(treeDTO.getUserId());
        treeEntity.setImageData(treeDTO.getImageData());
        // ...

        treeDAO.createTree(treeEntity);
    }


    public TreeEntity updateTree(TreeDTO treeDTO) {
        TreeEntity treeEntity = new TreeEntity();

        return treeDAO.updateTree(treeEntity);
    }

    public TreeEntity updateTreeData(Long userId, byte[] imageData) {
        return treeDAO.updateTreeData(userId, imageData);
    }

    public byte[] getImageDataByUserId(Long userId) {
        TreeEntity treeEntity = treeDAO.findByUserId(userId);
        if (treeEntity != null) {
            return treeEntity.getImageData();
        } else {
            return null;
        }
    }



    //

}
