package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.DBItem;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<DBItem, String>{	
	
	public DBItem findByGuid(String guid);
	
	public void deleteByGuid(String guid);

	public DBItem findByGuidAndEmail(String guid, String email);
	
	public List<DBItem> findByBrand(String brand);
	
	public List<DBItem> findByType(String type);
	
	public List<DBItem> findByBrandAndType(String brand, String type);
	
	@Query(value = "select * from public.items where brand in (:brand) or type in (:type)", nativeQuery = true)
	public Page<DBItem> search2(Pageable paging, @Param("brand") String brand, @Param("type") String type);
	
}
