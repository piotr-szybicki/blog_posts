var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "4920",
        "ok": "3396",
        "ko": "1524"
    },
    "minResponseTime": {
        "total": "82",
        "ok": "82",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "36124",
        "ok": "36124",
        "ko": "10033"
    },
    "meanResponseTime": {
        "total": "9697",
        "ok": "9560",
        "ko": "10001"
    },
    "standardDeviation": {
        "total": "6276",
        "ok": "7550",
        "ko": "1"
    },
    "percentiles1": {
        "total": "10001",
        "ok": "10606",
        "ko": "10001"
    },
    "percentiles2": {
        "total": "13426",
        "ok": "15581",
        "ko": "10001"
    },
    "percentiles3": {
        "total": "20789",
        "ok": "21601",
        "ko": "10002"
    },
    "percentiles4": {
        "total": "23746",
        "ok": "24608",
        "ko": "10004"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 814,
        "percentage": 17
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 86,
        "percentage": 2
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 2496,
        "percentage": 51
    },
    "group4": {
        "name": "failed",
        "count": 1524,
        "percentage": 31
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "18.779",
        "ok": "12.962",
        "ko": "5.817"
    }
},
contents: {
"req_request-1-46da4": {
        type: "REQUEST",
        name: "request_1",
path: "request_1",
pathFormatted: "req_request-1-46da4",
stats: {
    "name": "request_1",
    "numberOfRequests": {
        "total": "4920",
        "ok": "3396",
        "ko": "1524"
    },
    "minResponseTime": {
        "total": "82",
        "ok": "82",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "36124",
        "ok": "36124",
        "ko": "10033"
    },
    "meanResponseTime": {
        "total": "9697",
        "ok": "9560",
        "ko": "10001"
    },
    "standardDeviation": {
        "total": "6276",
        "ok": "7550",
        "ko": "1"
    },
    "percentiles1": {
        "total": "10001",
        "ok": "10606",
        "ko": "10001"
    },
    "percentiles2": {
        "total": "13426",
        "ok": "15581",
        "ko": "10001"
    },
    "percentiles3": {
        "total": "20789",
        "ok": "21601",
        "ko": "10002"
    },
    "percentiles4": {
        "total": "23746",
        "ok": "24608",
        "ko": "10004"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 814,
        "percentage": 17
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 86,
        "percentage": 2
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 2496,
        "percentage": 51
    },
    "group4": {
        "name": "failed",
        "count": 1524,
        "percentage": 31
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "18.779",
        "ok": "12.962",
        "ko": "5.817"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
