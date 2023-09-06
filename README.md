# website-banner
Through this APIs we can add and update website's main banner and bundle

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
