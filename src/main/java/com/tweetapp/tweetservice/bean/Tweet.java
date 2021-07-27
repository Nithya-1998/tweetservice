package com.tweetapp.tweetservice.bean;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.format.datetime.joda.LocalDateTimeParser;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedTimestamp;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTyped;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperFieldModel.DynamoDBAttributeType;
import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author Nithya T
 *
 */
@DynamoDBTable(tableName = "tweets")
public class Tweet {
	
	@DynamoDBHashKey(attributeName = "id")
	@DynamoDBAutoGeneratedKey
	@JsonAlias("_id")
	private String id;
	@DynamoDBAttribute(attributeName = "message")
	private String message;
	@DynamoDBAttribute(attributeName = "loginId")
	private String loginId;
	@DynamoDBAttribute(attributeName = "emailId")
	private String emailId;
	@DynamoDBAttribute(attributeName = "replyTweets")
	private List<ReplyTweet> replyTweets;
	@DynamoDBAttribute(attributeName = "postDTTM")
	private LocalDateTime postDTTM;
	@DynamoDBTyped(DynamoDBAttributeType.N)
	@DynamoDBAttribute(attributeName = "postLikeCount")
	private Long postLikeCount;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public List<ReplyTweet> getReplyTweets() {
		return replyTweets;
	}

	public void setReplyTweets(List<ReplyTweet> replyTweets) {
		this.replyTweets = replyTweets;
	}
	 @DynamoDBTypeConverted( converter = LocalDateTimeConverter.class )
	public LocalDateTime getPostDTTM() {
		return postDTTM;
	}

	public void setPostDTTM(LocalDateTime postDTTM) {
		this.postDTTM = postDTTM;
	}

	public Long getPostLikeCount() {
		return postLikeCount;
	}

	public void setPostLikeCount(Long postLikeCount) {
		this.postLikeCount = postLikeCount;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", message=" + message + ", loginId=" + loginId + ", emailId=" + emailId
				+ ", replyTweets=" + replyTweets + ", postDTTM=" + postDTTM + ", postLikeCount=" + postLikeCount + "]";
	}
	
	static public class LocalDateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {

        @Override
        public String convert( final LocalDateTime time ) {

            return time.toString();
        }

        @Override
        public LocalDateTime unconvert( final String stringValue ) {

            return LocalDateTime.parse(stringValue);
        }
    }

}
