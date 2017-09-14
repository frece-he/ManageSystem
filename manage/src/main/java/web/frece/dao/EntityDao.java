package web.frece.dao;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;



public interface EntityDao {
	/**
	 * 插入单条记录，database为默认DB，  接收参数类型为Map<String, Object>， 调用createEntity(String collection, Document entity)实现
	 * 
	 * @param collection
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public String createEntity(String collection, Map<String, Object> entity)throws Exception;
	/**
	 * 插入单条记录，database为默认DB，  接收参数类型为 Document类型
	 * 
	 * @param collection
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public String createEntity(String collection, Document entity)throws Exception;


	/**
	 * 插入多条记录， database为默认DB，接收参数类型为 List<Document>类型
	 * 
	 * @param collection
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public void createEntities(String collection, List<Document> documents)throws Exception;
	
	
	/**
	 * 更新多条数据， database为默认DB，Bson为过滤条件，  接收参数类型为Map<String, Object>， 调用doUpdate(String collection, Bson filter, Document param)实现
	 * 
	 * @param collection
	 * @param filter
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Document doUpdate(String collection, Bson filter, Map<String, Object> param) throws Exception;
	
	/**
	 * 更新多条数据， database为默认DB，Bson为过滤条件，  接收参数类型为 Document
	 * 
	 * @param collection
	 * @param filter
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Document doUpdate(String collection, Bson filter, Document param) throws Exception;

	
	/**
	 * 查询该collection下的所有数据
	 * 
	 * @param collection
	 * @return
	 * @throws Exception
	 */
	public List<Document> findAll(String collection)throws Exception;
	
	/**
	 * 根据参数条件查询，Map中的参数为 “and” 关系
	 * 
	 * @param collection
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public List<Document> getEntities(String collection, Map<String, Object> param)throws Exception;
	
	/**
	 *  根据条件查询指定collection中的数据，查询database 为默认DB
	 * 
	 * @param collection
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<Document> doSearch(String collection, Bson filter)throws Exception;
	

	/**
	 * 根据条件查询指定database与collection中的数据
	 * 
	 * @param database
	 * @param collection
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<Document> doSearch(String database, String collection, Bson filter)throws Exception;


	
	/**
	 * 软删除数据，根据过滤条件将指定数据的 "isDeleted"属性置为true
	 * 
	 * @param collection
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public Document removeEntities(String collection, Bson filter) throws Exception;
	/**
	 * 软删除数据，将指定collection中所有数据的 "isDeleted"属性置为true
	 * 
	 * @param collection
	 * @param filter
	 * @return
	 * @throws Exception
	 */
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
