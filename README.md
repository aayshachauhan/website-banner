# website-banner
Add and update website's main banner and bundle using Java 8 features
=====================================================================================
=====================================================================================
=====================================================================================
Write banner Request (post)

http://localhost:60001/v1/addBanners

All fields are mandatory except start date & End date (dates validation are different mention below) (valid request)

Data Validation 

1. websiteCode is validate with website_master (website_code).

	Valid sample Request
{
    "websiteCode": "Ezmall",
    "bannerTypes": "MAIN_BNR",
    "bannerTitle": "title",
    "bannerSubtitle": "title",
    "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "video_url" :"https://youtu.be/K3z9EmIsL98",
    "bannerSequence": "2",
    "isActive": "Y",
    "bannerLocation": "HOMEPAGE_SEC_01",
    "startDate": "",
    "endDate": ""
}

Response
{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 82
    }
}

    ->if we provide invalid websiteCode (invalid request)
{
    "websiteCode": "Hello",
         rest same data field and value of sample request

}

Response

{
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Banner validations failed. Please check codes and dates",
    "data": null
}

 2. bannerTypes is validate with banner_location_master(banner_bundle_type).
	
    Valid Request
    {
	 "bannerTypes": "MAIN_BNR",
         rest same data field and value of sample request

    }

    Response
    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 72,
        "bannerCreateDtos": null,
        "status": null
          }
    }


    Invalid request
{
     "bannerTypes": "Hello",
         rest same data field and value of sample request

    }
    
    Response

{
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Banner validations failed. Please check codes and dates",
    "data": null
}



 3. bundleLocation is validate with banner_location_master(bundle_banner_location_type).
   
    Valid Request
    {
	    "bannerLocation": "HOMEPAGE_SEC_01",
            rest same data field and value of sample request

    }

    Response
    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 72,
        "bannerCreateDtos": null,
        "status": null
          }
    }



    Invalid request

    {
        "bannerLocation": "location",
            rest same data field and value of sample request

    }

    Response

{
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Banner validations failed. Please check codes and dates",
    "data": null
}



4. All fields are required in request except the videoUrl,isActive, startDate, endDate.
    Valid Request
{
    "websiteCode": "BANNER",
    "bannerTypes": "MAIN_BNR",
    "bannerTitle": "title",
    "bannerSubtitle": "title",
    "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "video_url" :"",
    "bannerSequence": "2",
    "isActive": "",
    "bannerLocation": "HOMEPAGE_SEC_01",
    "startDate": "",
    "endDate": ""
}

Response
    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 83,
        "bannerCreateDtos": null,
        "status": null
          }
    }

##DATE Validation

4. if we provide only start date (valid request)
	
	sample Request
{
    "websiteCode": "Ezmall",
    "bannerTypes": "MAIN_BNR",
    "bannerTitle": "title",
    "bannerSubtitle": "title",
    "bannerImageCdnUrl": "url",
    "bannerLandingUrl": "url",
    "video_url" :"url",
    "bannerSequence": "2",
    "isActive": "Y",
    "bannerLocation": "HOMEPAGE_SEC_01",
    "startDate": "2023-03-23 10:10:10",
    "endDate": ""
}

    Response 
    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 72,
        "bannerCreateDtos": null,
        "status": null
          }
    }



5. if we provide only End date with Rest same field (invalid Request)
{
    "startDate": "",
    "endDate": "2023-03-23 10:10:10",
    rest same data field and value of sample request
}

Response

{
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Banner validations failed. Please check codes and dates",
    "data": null
}


6. Provide both start and end date and start date is before end date ( valid request)

{
    "startDate": "2023-03-23 10:10:10",
    "endDate": "2023-03-25 10:10:10",
    rest same data field and value of sample request

} 

 Response 
    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 72,
        "bannerCreateDtos": null,
        "status": null
          }
    }


7. start date can not be after end date (Invalid Request)
{
    "startDate": "2023-03-27 10:10:10",
    "endDate": "2023-03-25 10:10:10",
    rest same data field and value of sample request

}

Response

{
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Banner validations failed. Please check codes and dates",
    "data": null
}

8.If both dates are empty. (valid Request)
{
    "startDate": "",
    "endDate": "",
    rest same data field and value of sample request

}

Response 
    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 72,
        "bannerCreateDtos": null,
        "status": null
          }
    }

##To edit entity in DB

1 If we want to update banner title and subtitle (mendatory id, field websiteCode, bannerTypes and bannerLocation)
    valid request 
{
    "id" :3,
    "websiteCode": "Ezmall",
    "bannerTypes": "MAIN_BNR",
    "bannerTitle": "Hello",
    "bannerSubtitle": "Hi",
    "bannerLocation": "HOMEPAGE_SEC_01"
}


Response

{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 3
    }
}


2. if we provide invalid websiteCode (websiteCode is validate with website_master (website_code))

invalid request 

{
    "id" :3,
    "websiteCode": "Hello",
    "bannerTypes": "MAIN_BNR",
    "bannerTitle": "Hello",
    "bannerSubtitle": "Hi",
    "bannerLocation": "HOMEPAGE_SEC_01"
}

Response 
{
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Banner Edit validations failed. Please check codes and dates",
    "data": null
}


same with bannerTypes and bannerLocation as these are validate with their respective master table

3. if we provide only update website code and banner title with subtitle null (valid request)

{
    "id" :3,
    "websiteCode": "Ezmall",
    "bannerTypes": "MAIN_BNR",
    "bannerTitle": "Hi",
    "bannerSubtitle": "",
    "bannerLocation": "HOMEPAGE_SEC_01"
}

Response (only website code and banner title updated, subtitle remain same)
{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 3
    }
}

4. if we provide id that not exist in DB 

{
    "id" : 4,
    "websiteCode": "Ezmall",
    "bannerTypes": "MAIN_BNR",
    "bannerTitle": "",
    "bannerSubtitle": "hi",
    "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "video_url" :"https://youtu.be/K3z9EmIsL98",
    "bannerSequence": "2",
    "isActive": "Y",
    "bannerLocation": "HOMEPAGE_SEC_01",
    "startDate": "",
    "endDate": ""
}

Response

{
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Id not found",
    "data": null
}



------------------------------------------------------------
------------------------------------------------------------

Read Banner Api

Read Banner request (Get - fetch data from DB via ID )

http://localhost:60001/v1/readBanner

Provide valid ids (valid request)

Sample Request
{
    "ids" :[1,3],
    "isSyncToElkRequired" : true
}


Response 

{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "bannerCreateDtos": [
            {
                "id": "82",
                "websiteCode": "Ezmall",
                "bannerTypes": "MAIN_BNR",
                "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerSequence": "2",
                "bannerLocation": "HOMEPAGE_SEC_01",
                "bannerTitle": "title",
                "bannerSubtitle": "title",
                "isActive": "Y",
                "startDate": null,
                "endDate": null,
                "createDate": null,
                "modifiedDate": null,
                "createdBy": null,
                "modifiedBy": null
            },
            {
                "id": "84",
                "websiteCode": "Ezmall",
                "bannerTypes": "MAIN_BNR",
                "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerSequence": "2",
                "bannerLocation": "HOMEPAGE_SEC_01",
                "bannerTitle": "title",
                "bannerSubtitle": "title",
                "isActive": "Y",
                "startDate": "2023-03-22 00:00:00",
                "endDate": "2023-03-23 00:00:00",
                "createDate": null,
                "modifiedDate": null,
                "createdBy": null,
                "modifiedBy": null
            }
        ]
    }
}

isSyncToElkRequired Flag Validation 

1. if isSyncToElkRequired = true (Valid request and data save in elastic)

{
    "ids" :[1,3],
    "isSyncToElkRequired" : true
}

Response

{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "bannerCreateDtos": [
            {
                "id": "82",
                "websiteCode": "Ezmall",
                "bannerTypes": "MAIN_BNR",
                "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerSequence": "2",
                "bannerLocation": "HOMEPAGE_SEC_01",
                "bannerTitle": "title",
                "bannerSubtitle": "title",
                "isActive": "Y",
                "startDate": null,
                "endDate": null,
                "createDate": null,
                "modifiedDate": null,
                "createdBy": null,
                "modifiedBy": null
            },
            {
                "id": "84",
                "websiteCode": "Ezmall",
                "bannerTypes": "MAIN_BNR",
                "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerSequence": "2",
                "bannerLocation": "HOMEPAGE_SEC_01",
                "bannerTitle": "title",
                "bannerSubtitle": "title",
                "isActive": "Y",
                "startDate": "2023-03-22 00:00:00",
                "endDate": "2023-03-23 00:00:00",
                "createDate": null,
                "modifiedDate": null,
                "createdBy": null,
                "modifiedBy": null
            }
        ]
    }
}



2. if isSyncToElkRequired = false (Valid request and data not save in elastic)

 {
    "ids" :[1,3],
    "isSyncToElkRequired" : false
}

Response

{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "bannerCreateDtos": [
            {
                "id": "82",
                "websiteCode": "Ezmall",
                "bannerTypes": "MAIN_BNR",
                "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerSequence": "2",
                "bannerLocation": "HOMEPAGE_SEC_01",
                "bannerTitle": "title",
                "bannerSubtitle": "title",
                "isActive": "Y",
                "startDate": null,
                "endDate": null,
                "createDate": null,
                "modifiedDate": null,
                "createdBy": null,
                "modifiedBy": null
            },
            {
                "id": "84",
                "websiteCode": "Ezmall",
                "bannerTypes": "MAIN_BNR",
                "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
                "bannerSequence": "2",
                "bannerLocation": "HOMEPAGE_SEC_01",
                "bannerTitle": "title",
                "bannerSubtitle": "title",
                "isActive": "Y",
                "startDate": "2023-03-22 00:00:00",
                "endDate": "2023-03-23 00:00:00",
                "createDate": null,
                "modifiedDate": null,
                "createdBy": null,
                "modifiedBy": null
            }
        ]
    }
}

3. if isSyncToElkRequired =  -> InValid Request
    
    Response
{
    "status": "INTERNAL_SERVER_ERROR",
    "code": 5000,
    "message": "JSON parse error: Unexpected character ('}' (code 125)): expected a value; nested exception is com.fasterxml.jackson.core.JsonParseException: Unexpected character ('}' (code 125)): expected a value\n at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 4, column: 2]",
    "data": null
}



----------------------------------------------------------------------------
----------------------------------------------------------------------------
Convert Banner into list documentation


[{
    "websiteCode": "Ezmall",
    "bannerType": "MAIN_BNR",
    "bannerTitle": "Title",
    "bannerSubtitle": "hi",
    "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "video_url": "https://youtu.be/K3z9EmIsL98",
    "bannerSequence": "1",
    "isActive": "N",
    "bannerLocation": "HOMEPAGE_SEC_01",
    "startDate": "",
    "endDate": "",
    "catId" :""
},
{
    "id" :175,
    "websiteCode": "Ezmall",
    "bannerType": "MAIN_BNR",
    "bannerTitle": "Helo",
    "bannerSubtitle": "hi",
    "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "video_url": "https://youtu.be/K3z9EmIsL98",
    "bannerSequence": "2",
    "isActive": "",
    "bannerLocation": "HOMEPAGE_SEC_01",
    "startDate": "",
    "endDate": "",
    "catId" :""
}]

Response

{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "mapBannerId": {
            "NEW ID CREATED ": [
                178
            ],
            "UPDATED ID": [
                175
            ]
        }
    }
}

------------------------
[{
    "websiteCode": "Ezmall",
    "bannerType": "MAIN_BNR",
    "bannerTitle": "Title",
    "bannerSubtitle": "hi",
    "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "video_url": "https://youtu.be/K3z9EmIsL98",
    "bannerSequence": "1",
    "isActive": "N",
    "bannerLocation": "HOMEPAGE_SEC_01",
    "startDate": "",
    "endDate": "",
    "catId" :""
},
{

    "websiteCode": "Ezmall",
    "bannerType": "MAIN_BNR",
    "bannerTitle": "Helo",
    "bannerSubtitle": "hi",
    "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "video_url": "https://youtu.be/K3z9EmIsL98",
    "bannerSequence": "2",
    "isActive": "",
    "bannerLocation": "HOMEPAGE_SEC_01",
    "startDate": "",
    "endDate": "",
    "catId" :""
}]


Response

{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "mapBannerId": {
            "NEW ID CREATED ": [
                179,
                180
            ],
            "UPDATED ID": []
        }
    }
}

----------------------
Invalid Request
[{
    "websiteCode": "",
    "bannerType": "MAIN_BNR",
    "bannerTitle": "Title",
    "bannerSubtitle": "hi",
    "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "video_url": "https://youtu.be/K3z9EmIsL98",
    "bannerSequence": "1",
    "isActive": "N",
    "bannerLocation": "HOMEPAGE_SEC_01",
    "startDate": "",
    "endDate": "",
    "catId" :""
},
{

    "websiteCode": "",
    "bannerType": "MAIN_BNR",
    "bannerTitle": "Helo",
    "bannerSubtitle": "hi",
    "bannerImageCdnUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bannerLandingUrl": "https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "video_url": "https://youtu.be/K3z9EmIsL98",
    "bannerSequence": "2",
    "isActive": "",
    "bannerLocation": "HOMEPAGE_SEC_01",
    "startDate": "",
    "endDate": "",
    "catId" :""
}]

Response

{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {}
}


=====================================================================================
=====================================================================================
=====================================================================================



 Post -> http://localhost:60001/v1/addBundle

 Sample Request ->

    {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "Y",
    "startDate":"2023-03-21 08:34:13",
    "endDate":"2023-03-30 08:34:13"
}


Response ->

        {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 100
    }
}


   Date Validations ->

  1. if start date given and end date is not given -> Valid Condition.

-> sample Request
 {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "Y",
    "startDate":"2023-03-21 10:28:58",
    "endDate":""
}

-> Response
  {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 103
    }
}

  2. If start date is not given and end date is give -> Invalid Condition

    -> Request

    {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "Y",
    "startDate":"",
    "endDate":"2023-03-21 10:28:58"
}

    -> Response

    {
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Bundle validations failed. Please check codes and dates",
    "data": null
}

  3. both are provided (End date should be after start date) -> Valid Condition

  -> Request

  {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "Y",
    "startDate":"2023-03-21 10:28:58",
    "endDate":"2023-03-30 10:28:58"
}

    -> Response

    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 104
    }
}



  4. end  date can't be before than start date -> Invalid Condition

  -> Request

  {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "Y",
    "startDate":"2023-03-21 10:28:58",
    "endDate":"2023-03-20 10:28:58"
}

    -> Response

    {
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Bundle validations failed. Please check codes and dates",
    "data": null
}
 
  5. Both date are null -> Valid Condition

  ->Request

  {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "Y",
    "startDate":"",
    "endDate":""
}

    ->Response

    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 105
    }
}

----------------------------------------------------

 Data Validation in request-> 

 1. websiteCode is validate with website_master (website_code). 

    -> Valid Request

    {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "Y",
    "startDate":"",
    "endDate":""
}

    -> Response

    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 105
    }
}

    -> Invalid Request

    {
    "websiteCode": "DUMMY1",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "Y",
    "startDate":"",
    "endDate":""
}

    -> Response

    {
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Bundle validations failed. Please check codes and dates",
    "data": null
}

 2. bundleType is validate with banner_location_master(banner_bundle_type).

    -> Valid Request
    {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "Y",
    "startDate":"",
    "endDate":""
}

    -> Response

       {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 105
    }
}

    -> Invalid request

    {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL1", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "Y",
    "startDate":"",
    "endDate":""
}

    ->Response


    {
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Bundle validations failed. Please check codes and dates",
    "data": null
}



 3. bundleLocation is validate with banner_location_master(bundle_banner_location_type).

    -> Valid Request
    {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "Y",
    "startDate":"",
    "endDate":""
}

    ->Response
{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 105
    }
}

    ->Invalid Request
    {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.mp4",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web1",
    "isActive": "Y",
    "startDate":"",
    "endDate":""
}

    ->Response

        {
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Bundle validations failed. Please check codes and dates",
    "data": null
}



 4. Fields are required in request except the websiteCode,bundleType,bundleSequence, bundleLocation.

 -> Valid Request

 {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
}

    ->Response
    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 106
    }
}



    5. Validate between bundleImageCdnUrl, bundleLandingUrl, bundleProduct

        -> If user should pass - bundleProduct then bundleImageCdnUrl, bundleLandingUrl is not mandatory. -> Valid Request

->Request
{
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"",
    "bundleLandingUrl":"",
    "videoUrl":"",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
}

        ->Response
    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 106
    }
}

        -> If user should pass - bundleImageCdnUrl, bundleLandingUrl then bundleProduct is not mandatory. -> Valid Request

        ->Request
        {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
}

    ->Response
    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 106
    }
}

        -> if user not provide bundleProduct and bundleImageCdnUrl or bundleLandingUrl -> Invalid Request

            ->Request

{
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"",
    "bundleLandingUrl":"",
    "videoUrl":"",
    "bundleProduct":"",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
}

        ->Response

        {
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Request is invalid - product or landingUrl required",
    "data": null
}


 -> If user should pass - bundleImageCdnUrl, bundleLandingUrl and bundleProduct. -> Valid Request

        ->Request
        {
    "websiteCode": "DUMMY",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"Subtitle",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
}

    ->Response
    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 106
    }
}

---------------------------------------------------------------------------

Add edit portion

1. Check if id is given by the user.

    ->Request

    {
    "id":"1",
    "websiteCode": "Dummy",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
}

2. check If id is found in database or not 
    -> if id is present then update fields given  by the user.

    ->Valid Request

    {
    "id":"1",
    "websiteCode": "Dummy",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
}

    -> Response

    {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "id": 1
    }
}
        

        -> else throwing error Id not found.

        ->Request
        {
    "id":"7",
    "websiteCode": "Dummy",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"1",
    "bundleSequence":"3",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
}

 ->Response

 {
    "status": "BAD_REQUEST",
    "code": 4033,
    "message": "Id not found",
    "data": null
}

3. If fields are already have a data and the user request not mandatory to provide all details. they required only update fields and validation fields.




===================================================================================================================

Fetch data by id.

Get -> http://localhost:60001/v1/findByBundleId

Sample Request ->

{
 "ids" : [91,92],
 "isSyncToElkRequired": true
}

Response ->

{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "bundleDtos": [
            {
                "id": 91,
                "websiteCode": "Ezmall",
                "bundleType": "NEW_ARRIVALS",
                "bundleTitle": "Title",
                "bundleSubtitle": "Subtitle",
                "bundleImageCdnUrl": "ImageUrl",
                "bundleLandingUrl": "LandingUrl",
                "bundleProduct": "",
                "bundleSequence": 3,
                "bundleLocation": "HOMEPAGE_SEC_04",
                "isActive": "Y"
            },
            {
                "id": 92,
                "websiteCode": "Ezmall",
                "bundleType": "NEW_ARRIVALS",
                "bundleTitle": "Title",
                "bundleSubtitle": "Subtitle",
                "bundleImageCdnUrl": "ImageUrl",
                "bundleLandingUrl": "LandingUrl",
                "bundleProduct": "",
                "bundleSequence": 3,
                "bundleLocation": "HOMEPAGE_SEC_04",
                "isActive": "Y"
            }
        ]
    }
}


isSyncToElkRequired Validation ->

 1. if isSyncToElkRequired = true -> Valid condition and data save in elk service

 {
 "ids" : [91,92],
 "isSyncToElkRequired": true
}

Response ->

{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "bundleDtos": [
            {
                "id": 91,
                "websiteCode": "Ezmall",
                "bundleType": "NEW_ARRIVALS",
                "bundleTitle": "Title",
                "bundleSubtitle": "Subtitle",
                "bundleImageCdnUrl": "ImageUrl",
                "bundleLandingUrl": "LandingUrl",
                "bundleProduct": "",
                "bundleSequence": 3,
                "bundleLocation": "HOMEPAGE_SEC_04",
                "isActive": "Y"
            },
            {
                "id": 92,
                "websiteCode": "Ezmall",
                "bundleType": "NEW_ARRIVALS",
                "bundleTitle": "Title",
                "bundleSubtitle": "Subtitle",
                "bundleImageCdnUrl": "ImageUrl",
                "bundleLandingUrl": "LandingUrl",
                "bundleProduct": "",
                "bundleSequence": 3,
                "bundleLocation": "HOMEPAGE_SEC_04",
                "isActive": "Y"
            }
        ]
    }
}



 2. if isSyncToElkRequired = false -> Valid condition and data not save in elk service

 ->Request

 {
 "ids" : [92],
 "isSyncToElkRequired":false
}

->Response
{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "bundleDtos": [
            {
                "id": 92,
                "websiteCode": "Ezmall",
                "bundleType": "NEW_ARRIVALS",
                "bundleTitle": "Title",
                "bundleSubtitle": "Subtitle",
                "bundleImageCdnUrl": "ImageUrl",
                "bundleLandingUrl": "LandingUrl",
                "bundleProduct": "",
                "bundleSequence": 3,
                "bundleLocation": "HOMEPAGE_SEC_04",
                "isActive": "Y"
            }
        ]
    }
}

 3. if isSyncToElkRequired =  -> InValid condition

-> Response

{
    "status": "INTERNAL_SERVER_ERROR",
    "code": 5000,
    "message": "JSON parse error: Unexpected character ('}' (code 125)): expected a value; nested exception is com.fasterxml.jackson.core.JsonParseException: Unexpected character ('}' (code 125)): expected a value\n at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 4, column: 2]",
    "data": null
}

------------------------------------------------------------
------------------------------------------------------------
Convert bundle into list


Request ->

[{

    "websiteCode": "Dummy",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"hey",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"",
    "bundleSequence":"1",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
},
{
    "websiteCode": "Dummy",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"hey",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"",
    "bundleSequence":"1",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
}]

Response ->

{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "actionBundleIdMap": {
            "UPDATE": [],
            "INSERT": [
                249,
                250
            ]
        }
    }
}

------------------------------------
Request ->

[{
    "id":249,
    "websiteCode": "Dummy",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"hey",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"",
    "bundleSequence":"1",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
},
{
    "websiteCode": "Dummy",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"hey",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"",
    "bundleSequence":"1",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
}]



Response -> 

{
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {
        "actionBundleIdMap": {
            "UPDATE": [
                249
            ],
            "INSERT": [
                251
            ]
        }
    }
}

-------------------------

Invalid Request - >

[{
    "websiteCode": "ABCD",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"hey",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"",
    "bundleSequence":"1",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
},
{
    "websiteCode": "Dummy",  
    "bundleType": "DUMMY_BNDL", 
    "bundleTitle":"Dummy bundle",  
    "bundleSubtitle":"hey",
    "bundleImageCdnUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "bundleLandingUrl":"https://cdn3.ezmall.com/0/images/banner/ZSK1674624062574/r/1400x300/hero-banner.jpg",
    "videoUrl":"",
    "bundleProduct":"",
    "bundleSequence":"1",
    "bundleLocation":"Web",
    "isActive": "",
    "startDate":"",
    "endDate":""
}]

 Response ->

 {
    "status": "OK",
    "code": 2000,
    "message": "Request Successful",
    "data": {}
}
