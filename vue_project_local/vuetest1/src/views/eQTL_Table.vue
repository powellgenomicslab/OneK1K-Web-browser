<!--This vue script is the same as Association_Table_full.vue but this one display the top10 association data .-->

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
                        <a href="/eQTL_table_full"><h5>eQTL (Full)</h5></a>
                    </div>
                </div>

                <div class="w3-quarter">
                    <div class="w3-container w3-teal w3-padding-16">
                        <div class="w3-left"><i class="fa fa-line-chart w3-xxxlarge"></i></div>
                        <div class="w3-clear"></div>
                        <a href="/association_table"><h5>Association</h5></a>
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



            <!--        build table column names and assign an id to be fetched by script-->

                <table id="eQTL-table" class=" display center-all" style="width:100%">
                    <thead>
                    <tr>
                        <th>CELL TYPE</th>
                        <th>GENE</th>
                        <th>GENE ID</th>
                        <th>SNP ID</th>
                        <th>CHR</th>
                        <th>POS</th>
                        <th>ASSESSED ALLELE</th>
                        <th>OTHER ALLELE</th>
                        <th>CORR COEF</th>
                        <th>P-VALUE</th>
                        <th>FDR</th>
                    </tr>
                    </thead>

                </table>
            </div>

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
        var table =$('#eQTL-table').DataTable( {
            "ajax": {
                // the backend put the data for the table to this url, we are using ajax to get the data from backend and put into datatable at front end

                "url":"http://localhost:8181/eqtlTable_10",

                "dataSrc": ""},

// This function get the data from column 0 to build the options for the celltype pull down box

            initComplete: function () {
                this.api().columns([0]).every( function () {
                    var column = this;
                    var table = $('#eQTL-table').DataTable();

                    var select = $('<select id="celltype" class="celltype_box">' +
                        '<option default>All cell types</option></select>')
                        .insertBefore( $ (table.table().container()) )
                        .on( 'change', function () {
                            $('#eQTL-table').DataTable().draw();
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
                { "title": "CELL TYPE", "targets": 0 ,"searchable": true},
                { "title": "GENE", "targets": 1 },
                { "title": "GENE ID", "targets": 2 },
                { "title": "SNP ID", "targets": 3 },
                { "title": "CHR", "targets": 4 },
                { "title": "POS", "targets": 5 },
                { "title": "ASSESSED ALLELE", "targets": 6 },
                { "title": "OTHER ALLELE", "targets": 7 },
                { "title": "CORR COEF", "targets": 8 },
                { "title": "P-VALUE", "targets": 9 },
                { "title": "FDR", "targets": 10 },



            ],
            // This attribute decide which column to take from the backend

            "columns": [
                {data:'CELL_TYPE'},
                {data:'GENE'},
                {data:'GENE_ID'},
                {data:'SNP_ID'},
                {data:'CHR'},
                {data:'POS'},
                {data:'ASSESSED_ALLELE'},
                {data:'OTHER_ALLELE'},
                {data:'CORR_COEF'},
                {data:'P_VALUE'},
                {data:'FDR'},
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
                if (celltype !== searchData[0] && celltype!=="All cell types") {
                    hascelltype = false; //Doesn't match - don't display
                }



                // If either position or office matched then display the row
                return true ? hascelltype : false;
            });

    } );
</script>
