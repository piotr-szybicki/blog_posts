var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "4920",
        "ok": "2997",
        "ko": "1923"
    },
    "minResponseTime": {
        "total": "576",
        "ok": "576",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "60002",
        "ok": "28235",
        "ko": "60002"
    },
    "meanResponseTime": {
        "total": "11116",
        "ok": "11714",
        "ko": "10183"
    },
    "standardDeviation": {
        "total": "6696",
        "ok": "8178",
        "ko": "3011"
    },
    "percentiles1": {
        "total": "10001",
        "ok": "13262",
        "ko": "10001"
    },
    "percentiles2": {
        "total": "15271",
        "ok": "18481",
        "ko": "10001"
    },
    "percentiles3": {
        "total": "22760",
        "ok": "24022",
        "ko": "10003"
    },
    "percentiles4": {
        "total": "26032",
        "ok": "26383",
        "ko": "10012"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 328,
        "percentage": 7
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 310,
        "percentage": 6
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 2359,
        "percentage": 48
    },
    "group4": {
        "name": "failed",
        "count": 1923,
        "percentage": 39
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "18.636",
        "ok": "11.352",
        "ko": "7.284"
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
        "ok": "2997",
        "ko": "1923"
    },
    "minResponseTime": {
        "total": "576",
        "ok": "576",
        "ko": "10000"
    },
    "maxResponseTime": {
        "total": "60002",
        "ok": "28235",
        "ko": "60002"
    },
    "meanResponseTime": {
        "total": "11116",
        "ok": "11714",
        "ko": "10183"
    },
    "standardDeviation": {
        "total": "6696",
        "ok": "8178",
        "ko": "3011"
    },
    "percentiles1": {
        "total": "10001",
        "ok": "13262",
        "ko": "10001"
    },
    "percentiles2": {
        "total": "15271",
        "ok": "18481",
        "ko": "10001"
    },
    "percentiles3": {
        "total": "22760",
        "ok": "24022",
        "ko": "10003"
    },
    "percentiles4": {
        "total": "26032",
        "ok": "26383",
        "ko": "10012"
    },
    "group1": {
        "name": "t < 800 ms",
        "count": 328,
        "percentage": 7
    },
    "group2": {
        "name": "800 ms < t < 1200 ms",
        "count": 310,
        "percentage": 6
    },
    "group3": {
        "name": "t > 1200 ms",
        "count": 2359,
        "percentage": 48
    },
    "group4": {
        "name": "failed",
        "count": 1923,
        "percentage": 39
    },
    "meanNumberOfRequestsPerSecond": {
        "total": "18.636",
        "ok": "11.352",
        "ko": "7.284"
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
