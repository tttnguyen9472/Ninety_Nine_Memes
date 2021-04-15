import React, { useEffect, useState } from 'react';
import Fetch from '../fetch/Fetch';

function Comments(meme, dispatch) {
  function handleSubmit(event) {
    event.preventDefault();
    if (event.target.comment.value.length > 0) {
      /*Fetch('POST', '/meme/comment', {
      comment: event.target.comment.value,
      memeId: meme.id,
    })
      .then(response => dispatch({ type: 'FEED_STATE_CHANGE' }))
      .catch(err => dispatch({ type: 'FEED_ERROR', error: err }));*/
      event.target.comment.value = '';
      event.target.comment.placeholder = 'Write your Comment';
    } else {
      event.target.comment.placeholder =
        'Please, write your comment before sending it';
    }
  }

  return (
    <div>
      {meme.comments.map(comment => (
        <p>{comment}</p>
      ))}
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="comment"
          id="comment"
          placeholder="Write your Comment"
        ></input>
        <button type="submit"> Send Comment </button>
      </form>
    </div>
  );
}

export default Comments;
