package fa.training.spring.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CustomMongoQuery<T> {

	@Autowired
	MongoTemplate mongoTemplate;

	public void delete(String id, Class<T> target) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Update update = new Update();
		update.set("deleteStatus", true);
		mongoTemplate.updateMulti(query, update, target);

	}

}
