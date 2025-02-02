package tw.jessie.sideproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tw.jessie.sideproject.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    //擁有SQL基本語法
	List<Tag> findByTagname(String tagname);
}
