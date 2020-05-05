# tiktokapi-security

Contact me on Discord: https://discord.gg/qqpJra5
Contact me on email: lcfcnd1@hotmail.com

## Login Services
Login services are now avilables on this repo: [TikTokLoginServices](https://github.com/LCSP/TikTokLoginServices)


-----
Java class of the app (TikTok) that creates the "anti-spam" parameters:

    ts
    as
    cp
    mas
Using just the parameters `cp` `as` you can retrieve profile info, searching and posts lists.

For user actions like login, posting, commenting, etc. `ts` `as` `cp` `mas` parameters are needed. 

## Posts, Profile Info, Searching.

For `cp` and `as` in the `C2071b.java` you can see that in the line `162` there is the parameters definition ( `str22 = str + "&as=a1qwert123&cp=cbfhckdckkde1";` ) in case that the if in the line `156` can't be true. That means that the parameters `&as=a1qwert123` and `&cp=cbfhckdckkde1` can be used without any risk of expiration.

## User Actions (Login, Posting, Commenting).
Since I didn't know that much of Java I didn't bother to reverse the code but if you find out how this work you are free to make a Pull Request to add it to the repository.

# API Links

## Searching:

Arguments:
 `&keyword` needs to have the text to search)



## Profile Info:

 Arguments:
  `&user_id` needs to have the unique_id of the profile)

## Posts:

Arguments:
 `user_id` : unique_id of the target profile.
 `count` : The amount of videos to retrieve (max 20)
`max_cursor` : The first time fetching the posts this arguments needs to be 0, when the videos are retrieved in the json response there is a value called `max_cursor` that value should be the `max_cursor` for the next time you fetch the posts, this argument make the TikTok Api retrieve the videos after the ones you already have.
