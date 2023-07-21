package pack.springjpa1.data.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pack.springjpa1.data.entity.TreeEntity;
import pack.springjpa1.data.repository.TreeRepository;

@Repository
public class TreeDAO {
    private final TreeRepository treeRepository;

    @Autowired
    public TreeDAO(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    public void createTree(TreeEntity treeEntity) {
        this.treeRepository.save(treeEntity);
    }

    public TreeEntity updateTree(TreeEntity treeEntity) {
        return this.treeRepository.save(treeEntity);
    }

    public TreeEntity updateTreeData(Long userId, byte[] imageData) {
        TreeEntity treeEntity = (TreeEntity) this.treeRepository.findByUserId(userId);
        treeEntity.setImageData(imageData);
        return this.treeRepository.save(treeEntity);
    }
    public TreeEntity findByUserId(Long userId) {
        return this.treeRepository.findByUserId(userId);
    }



}

