package com.tweetapp.tweetservice.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.tweetapp.tweetservice.bean.ReplyTweet;
import com.tweetapp.tweetservice.bean.Tweet;
import com.tweetapp.tweetservice.dto.LikeCountDto;
import com.tweetapp.tweetservice.dto.TweetDto;
import com.tweetapp.tweetservice.exception.Message;
import com.tweetapp.tweetservice.exception.TweetNotFoundException;
import com.tweetapp.tweetservice.repository.TweetRepository;

/**
 * @author Nithya T
 */
@Service
public class TweetService {

	public static final Logger LOGGER = LoggerFactory.getLogger(TweetService.class);

	@Autowired
	public TweetRepository tweetRepository;

	@Transactional
	public List<Tweet> getAllTweets() {
		Iterable<Tweet> tweets = new ArrayList<Tweet>();
		try {
			tweets = tweetRepository.findAll();
		} catch (Exception e) {
			LOGGER.info("Internal server error");
		}
		return  (List<Tweet>) tweets;
	}

	@Transactional
	public List<Tweet> getAllTweetsById(String loginId) throws TweetNotFoundException {
		List<Tweet> tweets = tweetRepository.findByLoginId(loginId);
		if (CollectionUtils.isEmpty(tweets)) {
			throw new TweetNotFoundException("Tweet not found");
		}
		return tweets;
	}

	@Transactional
	public TweetDto postTweet(Tweet tweet) {
		tweet.setPostDTTM(LocalDateTime.now());
		tweetRepository.save(tweet);
		return new TweetDto(tweet.getId(), "Tweet inserted successfully");
	}

	@Transactional
	public Tweet getTweetById(String id) {
		Tweet tweet = tweetRepository.findById(id).get();
		return tweet;
	}

	@Transactional
	public Tweet replyPostTweet(ReplyTweet replyTweet, String loginId, String id) {
		Tweet tweet = getTweetById(id);
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		replyTweet.setId(uuidAsString);
		replyTweet.setRepliedDTTM(LocalDateTime.now());
		replyTweet.setLoginId(loginId);
		tweet.getReplyTweets().add(replyTweet);
		tweetRepository.save(tweet);
		return tweet;
	}

	@Transactional
	public Tweet updateTweet(Tweet tweet, String loginId, String id) {
		Tweet oldtweet = tweetRepository.findById(id).get();
		if (oldtweet != null) {
			tweet.setPostDTTM(LocalDateTime.now());
			tweetRepository.save(tweet);
			return tweet;
		} else {
			return tweet;
		}
	}

	@Transactional
	public Message deleteTweet(String loginId, String id) {
		Tweet tweet = tweetRepository.findByIdAndLoginId(id, loginId);
		if (tweet != null) {
			tweetRepository.deleteById(id);
			return new Message("Tweet Deleted Successfully");
		} else {
			return new Message("No Tweet found");
		}
	}

	@Transactional
	public Tweet likeTweetUpdate(LikeCountDto likeCount, String loginId, String id) {
		Tweet oldtweet = tweetRepository.findById(id).get();
		oldtweet.setPostLikeCount(likeCount.getCount());
		tweetRepository.save(oldtweet);
		return oldtweet;
	}

}
