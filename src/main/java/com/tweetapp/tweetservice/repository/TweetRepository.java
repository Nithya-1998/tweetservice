package com.tweetapp.tweetservice.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import com.tweetapp.tweetservice.bean.Tweet;

/**
 * @author Nithya T
 *
 */
@EnableScan
public interface TweetRepository extends CrudRepository<Tweet, String> {

	Tweet findByIdAndLoginId(String id, String loginId);
	
	List<Tweet> findByLoginId(String loginId);

}
