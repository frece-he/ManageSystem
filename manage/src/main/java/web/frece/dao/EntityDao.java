package web.frece.dao;

import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;



public interface EntityDao {
	public String createEntity(String collection, Map<String, Object> entity)throws Exception;
	public String createEntity(String collection, Document entity)throws Exception;

	
	public Document doUpdate(String collection, Bson filter, Map<String, Object> param) throws Exception;
	public Document doUpdate(String collection, Bson filter, Document param) throws Exception;
	
	public Document findAll(String collection)throws Exception;
	public Document getEntities(String collection, Map<String, Object> param)throws Exception;
	public Document doSearch(String collection, Bson filter)throws Exception;
	public Document doSearch(String database, String collection, Bson filter)throws Exception;
	
	
	public Document removeEntities(String collection, Bson filter) throws Exception;
	public Document removeAll(String collection) throws Exception;
	
	
	
	/**
	 * @deprecated 测试用 根据条件删除数据
	 */
	public String deleteEntity(String collection, Bson filter)throws Exception;
	/**
	 * @deprecated 测试用 删除数据表
	 */
	public String deleteAll(String collection)throws Exception;
}
