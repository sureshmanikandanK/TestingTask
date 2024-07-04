/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 96.66666666666667, "KoPercent": 3.3333333333333335};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.85, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "ValueAnalysis-1"], "isController": false}, {"data": [1.0, 500, 1500, "ValueAnalysis-0"], "isController": false}, {"data": [1.0, 500, 1500, "ProductDesign-0"], "isController": false}, {"data": [1.0, 500, 1500, "ProductDesign-1"], "isController": false}, {"data": [0.5, 500, 1500, "Intelligence"], "isController": false}, {"data": [1.0, 500, 1500, "DigitalAssurance-1"], "isController": false}, {"data": [1.0, 500, 1500, "DesignAutomation-1"], "isController": false}, {"data": [1.0, 500, 1500, "DigitalAssurance-0"], "isController": false}, {"data": [1.0, 500, 1500, "DesignAutomation-0"], "isController": false}, {"data": [1.0, 500, 1500, "ElectricalCAD-1"], "isController": false}, {"data": [1.0, 500, 1500, "ElectricalCAD-0"], "isController": false}, {"data": [1.0, 500, 1500, "ProductDesign"], "isController": false}, {"data": [1.0, 500, 1500, "EngineeringAnalysis-1"], "isController": false}, {"data": [1.0, 500, 1500, "EngineeringAnalysis-0"], "isController": false}, {"data": [0.0, 500, 1500, "CloudServices"], "isController": false}, {"data": [1.0, 500, 1500, "DataManagement"], "isController": false}, {"data": [0.5, 500, 1500, "CustomerExperience"], "isController": false}, {"data": [1.0, 500, 1500, "DesignAutomation"], "isController": false}, {"data": [1.0, 500, 1500, "DataManagement-1"], "isController": false}, {"data": [1.0, 500, 1500, "DataManagement-0"], "isController": false}, {"data": [1.0, 500, 1500, "EngineeringAnalysis"], "isController": false}, {"data": [1.0, 500, 1500, "ValueAnalysis"], "isController": false}, {"data": [1.0, 500, 1500, "CustomerExperience-0"], "isController": false}, {"data": [0.5, 500, 1500, "CustomerExperience-1"], "isController": false}, {"data": [1.0, 500, 1500, "ElectricalCAD"], "isController": false}, {"data": [0.5, 500, 1500, "CloudServices-0"], "isController": false}, {"data": [0.0, 500, 1500, "CloudServices-1"], "isController": false}, {"data": [1.0, 500, 1500, "Intelligence-0"], "isController": false}, {"data": [0.5, 500, 1500, "Intelligence-1"], "isController": false}, {"data": [1.0, 500, 1500, "DigitalAssurance"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 30, 1, 3.3333333333333335, 510.5666666666667, 191, 2824, 210.0, 1134.9000000000005, 2533.0499999999997, 2824.0, 3.8945865247306246, 105.14420112293911, 0.802244255484876], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["ValueAnalysis-1", 1, 0, 0.0, 206.0, 206, 206, 206.0, 206.0, 206.0, 206.0, 4.854368932038835, 110.90147148058253, 0.7347921723300971], "isController": false}, {"data": ["ValueAnalysis-0", 1, 0, 0.0, 201.0, 201, 201, 201.0, 201.0, 201.0, 201.0, 4.975124378109452, 2.827658582089552, 0.7530705845771144], "isController": false}, {"data": ["ProductDesign-0", 1, 0, 0.0, 208.0, 208, 208, 208.0, 208.0, 208.0, 208.0, 4.807692307692308, 2.732496995192308, 0.7277268629807693], "isController": false}, {"data": ["ProductDesign-1", 1, 0, 0.0, 191.0, 191, 191, 191.0, 191.0, 191.0, 191.0, 5.235602094240838, 122.83724640052355, 0.7924983638743456], "isController": false}, {"data": ["Intelligence", 1, 0, 0.0, 812.0, 812, 812, 812.0, 812.0, 812.0, 812.0, 1.2315270935960592, 83.52134967672413, 0.36560960591133], "isController": false}, {"data": ["DigitalAssurance-1", 1, 0, 0.0, 210.0, 210, 210, 210.0, 210.0, 210.0, 210.0, 4.761904761904763, 242.3828125, 0.68359375], "isController": false}, {"data": ["DesignAutomation-1", 1, 0, 0.0, 203.0, 203, 203, 203.0, 203.0, 203.0, 203.0, 4.926108374384237, 109.80795874384236, 0.7600831280788177], "isController": false}, {"data": ["DigitalAssurance-0", 1, 0, 0.0, 210.0, 210, 210, 210.0, 210.0, 210.0, 210.0, 4.761904761904763, 2.6320684523809526, 0.68359375], "isController": false}, {"data": ["DesignAutomation-0", 1, 0, 0.0, 208.0, 208, 208, 208.0, 208.0, 208.0, 208.0, 4.807692307692308, 2.7606670673076925, 0.7418118990384616], "isController": false}, {"data": ["ElectricalCAD-1", 1, 0, 0.0, 202.0, 202, 202, 202.0, 202.0, 202.0, 202.0, 4.9504950495049505, 106.85624226485147, 0.7493425123762376], "isController": false}, {"data": ["ElectricalCAD-0", 1, 0, 0.0, 210.0, 210, 210, 210.0, 210.0, 210.0, 210.0, 4.761904761904763, 2.7064732142857144, 0.7207961309523809], "isController": false}, {"data": ["ProductDesign", 1, 0, 0.0, 400.0, 400, 400, 400.0, 400.0, 400.0, 400.0, 2.5, 60.07568359375, 0.7568359375], "isController": false}, {"data": ["EngineeringAnalysis-1", 1, 0, 0.0, 201.0, 201, 201, 201.0, 201.0, 201.0, 201.0, 4.975124378109452, 106.31413246268656, 0.7822217039800995], "isController": false}, {"data": ["EngineeringAnalysis-0", 1, 0, 0.0, 209.0, 209, 209, 209.0, 209.0, 209.0, 209.0, 4.784688995215311, 2.775493421052632, 0.7522802033492824], "isController": false}, {"data": ["CloudServices", 1, 1, 100.0, 2824.0, 2824, 2824, 2824.0, 2824.0, 2824.0, 2824.0, 0.3541076487252125, 23.212033020538247, 0.12034127124645894], "isController": false}, {"data": ["DataManagement", 1, 0, 0.0, 411.0, 411, 411, 411.0, 411.0, 411.0, 411.0, 2.4330900243309004, 67.03828315085158, 0.7983576642335767], "isController": false}, {"data": ["CustomerExperience", 1, 0, 0.0, 1156.0, 1156, 1156, 1156.0, 1156.0, 1156.0, 1156.0, 0.8650519031141869, 67.62526357050173, 0.26526005622837373], "isController": false}, {"data": ["DesignAutomation", 1, 0, 0.0, 411.0, 411, 411, 411.0, 411.0, 411.0, 411.0, 2.4330900243309004, 55.63317366180049, 0.7508363746958638], "isController": false}, {"data": ["DataManagement-1", 1, 0, 0.0, 202.0, 202, 202, 202.0, 202.0, 202.0, 202.0, 4.9504950495049505, 133.4603186881188, 0.8121905940594059], "isController": false}, {"data": ["DataManagement-0", 1, 0, 0.0, 208.0, 208, 208, 208.0, 208.0, 208.0, 208.0, 4.807692307692308, 2.854567307692308, 0.7887620192307693], "isController": false}, {"data": ["EngineeringAnalysis", 1, 0, 0.0, 410.0, 410, 410, 410.0, 410.0, 410.0, 410.0, 2.4390243902439024, 53.534679878048784, 0.7669588414634146], "isController": false}, {"data": ["ValueAnalysis", 1, 0, 0.0, 408.0, 408, 408, 408.0, 408.0, 408.0, 408.0, 2.450980392156863, 57.3874080882353, 0.7419960171568628], "isController": false}, {"data": ["CustomerExperience-0", 1, 0, 0.0, 210.0, 210, 210, 210.0, 210.0, 210.0, 210.0, 4.761904761904763, 2.7250744047619047, 0.7300967261904762], "isController": false}, {"data": ["CustomerExperience-1", 1, 0, 0.0, 945.0, 945, 945, 945.0, 945.0, 945.0, 945.0, 1.0582010582010584, 82.11908895502646, 0.16224371693121695], "isController": false}, {"data": ["ElectricalCAD", 1, 0, 0.0, 412.0, 412, 412, 412.0, 412.0, 412.0, 412.0, 2.4271844660194173, 53.770194933252434, 0.7347921723300971], "isController": false}, {"data": ["CloudServices-0", 1, 0, 0.0, 522.0, 522, 522, 522.0, 522.0, 522.0, 522.0, 1.9157088122605364, 1.049524066091954, 0.38164511494252873], "isController": false}, {"data": ["CloudServices-1", 1, 0, 0.0, 2295.0, 2295, 2295, 2295.0, 2295.0, 2295.0, 2295.0, 0.43572984749455335, 28.323716639433552, 0.06127450980392157], "isController": false}, {"data": ["Intelligence-0", 1, 0, 0.0, 210.0, 210, 210, 210.0, 210.0, 210.0, 210.0, 4.761904761904763, 2.678571428571429, 0.7068452380952381], "isController": false}, {"data": ["Intelligence-1", 1, 0, 0.0, 602.0, 602, 602, 602.0, 602.0, 602.0, 602.0, 1.6611295681063123, 111.72231883305648, 0.24657392026578073], "isController": false}, {"data": ["DigitalAssurance", 1, 0, 0.0, 420.0, 420, 420, 420.0, 420.0, 420.0, 420.0, 2.3809523809523814, 122.50744047619048, 0.68359375], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["The operation lasted too long: It took 2,824 milliseconds, but should not have lasted longer than 2,000 milliseconds.", 1, 100.0, 3.3333333333333335], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 30, 1, "The operation lasted too long: It took 2,824 milliseconds, but should not have lasted longer than 2,000 milliseconds.", 1, "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["CloudServices", 1, 1, "The operation lasted too long: It took 2,824 milliseconds, but should not have lasted longer than 2,000 milliseconds.", 1, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
