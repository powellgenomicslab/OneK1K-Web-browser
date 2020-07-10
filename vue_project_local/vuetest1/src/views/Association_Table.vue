<!--This vue script is the same as Association_Table_full.vue but this one route the top10 association data .-->
<template>
<!--    navigation top bar-->
    <div class="w3-container"  style="margin-left:300px;margin-top:43px;">
        <div class="w3-row-padding w3-margin-bottom">
            <div class="w3-quarter">
                <div class="w3-container w3-red w3-padding-16">
                    <div class="w3-left"><i class="fa fa-home w3-xxxlarge"></i></div>
                    <div class="w3-clear"></div>
                    <a href="/" > <h5>Home</h5> </a>
                </div>
            </div>

            <div class="w3-quarter">
                <div class="w3-container w3-blue w3-padding-16">
                    <div class="w3-left"><i class="fa fa-table w3-xxxlarge"></i></div>
                    <div class="w3-clear"></div>
                    <a href="/eQTL_table"><h5>eQTL </h5></a>
                </div>
            </div>

            <div class="w3-quarter">
                <div class="w3-container w3-teal w3-padding-16">
                    <div class="w3-left"><i class="fa fa-line-chart w3-xxxlarge"></i></div>
                    <div class="w3-clear"></div>
                    <a href="/association_table_full"><h5>Association (Full)</h5></a>
                </div>
            </div>

            <div class="w3-quarter">
                <div class="w3-container w3-orange w3-text-white w3-padding-16">
                    <div class="w3-left"><i class="fa fa-pie-chart w3-xxxlarge"></i></div>
                    <div class="w3-clear"></div>
                    <a href="/expression"><h5>Expression Graph</h5></a>
                </div>
            </div>
        </div>

        <!--    navigation top bar end-->

<!--build the search, fileupload and cell type drop down buttons.-->
        <div class="w3-container" >
            <span class="table-search-bar">SNP or Gene Search <input type="text" id="mySearch"  placeholder="ARSA or rs8142033"></span>
            <span class="upload_file">***Please upload a text file with your snps separated by newline</span>
            <br>


            <br><span class="celltype-wrap">Cell Type</span>
<!--            assign a function name so can be called in script and assign an id to the uploaded file called input-->
            <span class="upload_file"><input type="file" id="input" @change="fileUpload">
                <button type="button" id="search">Search in table</button></span>
        </div>
<!--        build table column names and assign an id to be fetched by script-->
        <table id="association-table" class=" display center-all" style="width:100%">
            <thead>
            <tr>
                <th>GENE</th>
                <th>SNP</th>
                <th>CHR</th>
                <th>BP</th>
                <th>ALLELE 1 (A1)</th>
                <th>ALLELE 2 (A2)</th>
                <th>A2 FREQUENCY ONEK1K</th>
                <th>A2 FREQUENCY HRC</th>
                <th>SPEARMANS RHO</th>
                <th>SUMMARY STATISTICS</th>
                <th>P-VALUE</th>
                <th>FDR</th>
                <th>R SQAURE</th>
                <th>GENOTYPED</th>
                <th>Cell Type</th>
            </tr>
            </thead>

        </table>

    </div>
</template>

<script>
    var search_file_arr_final
    var search_file
    var search_file_arr
    var hascelltype
    var searchString
    export default {
        methods: {
            // fileupload function, this function read the uploaded file, trim the content into arrary and search in the datatable
            fileUpload() {
                let file=$("#input")[0].files[0];
                var reader=new FileReader;
                reader.readAsText(file,'utf-8');
                reader.onload=function(f){
                    search_file=reader.result
                    search_file_arr=search_file.split("\n")
                    search_file_arr_final = []
                    for (var i in search_file_arr){
                        search_file_arr_final.push(search_file_arr[i].trim())
                    }
                    search_file_arr=search_file_arr_final.join(',');
                    //search_file_arr=search_file_arr.slice(0,-1);
                    searchString='(';
                    //searchString = '(' + search_file_arr.split(',').join('|') + ')';
                    for(var s in search_file_arr.split(',')){
                        searchString=searchString+'^'+search_file_arr.split(',')[s]+'$|'
                    }
                    searchString=searchString.slice(0,-1)
                    searchString=searchString+')'
                }
            }
        }
    }
 // The below code establish the table using datatable plugin
    $(document).ready(function() {
        $('#association-table').DataTable( {

            // the backend put the data for the table to this url, we are using ajax to get the data from backend and put into datatable at front end
            "ajax": {
                "url":"http://localhost:8181/association_table_10",

                "dataSrc": ""},

// This function get the data from column 14 to build the options for the celltype pull down box
            initComplete: function () {
                this.api().columns([14]).every( function () {
                    var column = this;
                    var table = $('#association-table').DataTable();

                    var select = $('<select id="celltype"><option default>All cell types</option></select>')
                        .insertBefore( $ (table.table().container()) )
                        .on( 'change', function () {
                            $('#association-table').DataTable().draw();
                        } );

                    column.data().unique().sort().each( function ( d, j ) {
                        select.append( '<option value="'+d+'">'+d+'</option>' );
                    } );
                } );
            },

            // this build for the download button for datatable, the format can be csv, excel and pdf
            buttons: [
                'csv','excel', 'pdf'
            ],


// This attribute define column names and which column to put data. The names need to be the same with the declared varaible from backend
            "columnDefs": [
                { "title": "GENE", "targets": 0 },
                { "title": "SNP", "targets": 1 },
                { "title": "CHR", "targets": 2 },
                { "title": "BP", "targets": 3 },
                { "title": "ALLELE 1 (A1)", "targets": 4 },
                { "title": "ALLELE 2 (A2)", "targets": 5 },
                { "title": "A2 FREQUENCY ONEK1K", "targets": 6 },
                { "title": "A2 FREQUENCY HRC", "targets": 7 },
                { "title": "SPEARMANS RHO", "targets": 8 },
                { "title": "SUMMARY STATISTICS", "targets": 9 },
                { "title": "P-VALUE", "targets": 10 },
                { "title": "FDR", "targets": 11 },
                { "title": "R SQAURE", "targets": 12 },
                { "title": "GENOTYPED", "targets": 13 },
                { "title": "Cell Type", "targets": 14 ,"searchable": true}




            ],

            // This attribute decide which column to take from the backend

            "columns": [
                {data:'GENE'},
                {data:'SNP'},
                {data:'CHR'},
                {data:'BP'},
                {data:'A1'},
                {data:'A2'},
                {data:'A2_FREQ_ONEK1K'},
                {data:'A2_FREQ_HRC'},
                {data:'SPEARMANS_RHO'},
                {data:'S_STATISTICS'},
                {data:'P_VALUE'},
                {data:'FDR'},
                {data:'RSQUARE'},
                {data:'GENOTYPED'},
                {data:'Cell_type'}
            ],

            "scrollY": '800px',
            "scrollX": true,
            "scrollCollapse": true,
            "pageLength": 50,
            "bLengthChange": false,
            "dom": 'lrtipB',
            "deferRender": true,

        } );


        $('.dataTables_filter').addClass('pull-left clearfix');



// build the search button for searching the whole table
        $('#mySearch').on('keyup redraw', function() {
            table.search($('#mySearch').val(), true,true).draw(true);
        });
        // build the search function connected to the upload function for searching SNP  column

        $('#search').on("click redraw", function() {
            console.log(searchString)

            table.columns(3).search(searchString, true,true).draw(true);
        });

// build the searching the celltype function and connect to the pulldown box

        $.fn.dataTable.ext.search.push(
            function( settings, searchData, index, rowData, counter ) {
                var celltype = $( "#celltype option:selected" ).text();

                // Display the row if both inputs are empty
                if (celltype.length ===0) {
                    return true;
                }

                // Display row if position matches position selection
                hascelltype = true;

                if (celltype !== searchData[14] && celltype!=="All cell types") {
                    hascelltype = false; //Doesn't match - don't display
                }



                // If either position or office matched then display the row
                return true ? hascelltype : false;
            });

    } );
</script>
