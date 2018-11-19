# tiktokapi-security

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

	https://api2.musical.ly/aweme/v1/discover/search/?cursor=0&keyword=TODO&count=1&type=1&is_pull_refresh=0&hot_search=0&_rticket=1542368732184611792&ac=wifi&aid=1128&app_name=aweme&as=a1qwert123&channel=360&cp=cbfhckdckkde1&device_brand=OnePlus&device_id=59522398368&device_platform=android&device_type=ONEPLUS%2BA5000&dpi=420&iid=51383881009&language=zh&manifest_version_code=169&max_cursor=1540828257000&openudid=7486790324239476&os_api=27&os_version=8.1.0&resolution=1080%2A1920&retry_type=no_retry&ssmix=a&ts=1542368732&update_version_code=1692&uuid=396049154362332&version_code=169&version_name=1.6.9

## Profile Info:

 Arguments:
  `&user_id` needs to have the unique_id of the profile)

    https://api2.musical.ly/aweme/v1/user/?_rticket=1542369648297554283&ac=wifi&aid=1128&app_name=aweme&as=a1qwert123&channel=360&cp=cbfhckdckkde1&device_brand=OnePlus&device_id=59523162552&device_platform=android&device_type=ONEPLUS%2BA5000&dpi=420&iid=51386286242&language=zh&manifest_version_code=169&openudid=0776236926577490&os_api=27&os_version=8.1.0&resolution=1080%2A1920&retry_type=no_retry&ssmix=a&ts=1542369648&update_version_code=1692&user_id=TODO&uuid=067400454346124&version_code=169&version_name=1.6.9

## Posts:

Arguments:
 `user_id` : unique_id of the target profile.
 `count` : The amount of videos to retrieve (max 20)
`max_cursor` : The first time fetching the posts this arguments needs to be 0, when the videos are retrieved in the json response there is a value called `max_cursor` that value should be the `max_cursor` for the next time you fetch the posts, this argument make the TikTok Api retrieve the videos after the ones you already have.

    https://api2.musical.ly/aweme/v1/aweme/post/?_rticket=1542368732184611792&ac=wifi&aid=1128&app_name=aweme&as=a1qwert123&channel=360&count=TODO&cp=cbfhckdckkde1&device_brand=OnePlus&device_id=59522398368&device_platform=android&device_type=ONEPLUS%2BA5000&dpi=420&iid=51383881009&language=zh&manifest_version_code=169&max_cursor=TODO&openudid=7486790324239476&os_api=27&os_version=8.1.0&resolution=1080%2A1920&retry_type=no_retry&ssmix=a&ts=1542368732&update_version_code=1692&user_id=TODO&uuid=396049154362332&version_code=169&version_name=1.6.9

# More Info
To get more info on how the app makes calls use "SSL Capture" on Android and put the filter to the TikTok App.

And for the App code, just decompile it using jadx.

Don't pay for Api information. Here you have everything. And if you have some Android-Java knowledge take a loot at the java file to find out how it work.

If you are a TikTok dev and you want this repository to be taken down just contact me and we will figure out what to do.
