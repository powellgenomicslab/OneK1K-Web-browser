<!--This vue script is the same as eQTL_Table.vue but this one route the full eQTL data (in total of 22 chromosomes).-->
<!--So the backend send the whole dataset to a different url and this code fetch the data from there.-->
<template>
    <div class="w3-container" data-id="show-table" style="margin-left:300px;margin-top:43px;">
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
                    <a href="/eQTL_table"><h5>eQTL</h5></a>
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

        <div class="w3-container" >

            <span class="table-search-bar">SNP or Gene Search <input type="text" id="mySearch"  placeholder="ARSA or rs8142033"></span>
            <span class="upload_file">***Please upload a text file with your snps separated by newline</span>
            <br>

            <br><span class="celltype-wrap">Cell Type</span>
            <span class="upload_file"><input type="file" id="input" @change="fileUpload">
                <button type="button" id="search">Search in table</button></span>




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
            fileUpload() {

                let file=$("#input")[0].files[0];
                // var reader=new FileReader;
                // reader.readAsText(file,'utf-8');
                // reader.onload=function(f){
                //     search_file=reader.result
                //     search_file_arr=search_file.split("\n")
                //     console.log(search_file_arr);
                //     search_file_arr=search_file_arr.join(',');
                //     search_file_arr=search_file_arr.slice(0,-1);
                //     console.log(search_file_arr);
                //     document.getElementById('mySearch').value=search_file_arr;

                //}
            }
        }
    }

    $(document).ready(function() {
        var table = $('#eQTL-table').DataTable( {
            "ajax": {
                "url":"http://localhost:8181/eqtlTable",

                "dataSrc": ""},


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



            buttons: [
                'csv','excel', 'pdf',
            ],


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


        $('#mySearch').on('keyup redraw', function() {
            console.log("here")
            searchString = '(' + $('#mySearch').val().split(',').join('|') + ')';
            console.log(searchString)
            table.search(searchString, true).draw(true);
        });
        $('#search').on("click redraw", function() {
            console.log("here")
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
                console.log(search_file_arr)
                search_file_arr=search_file_arr.slice(0,-1);

                searchString = '(' + search_file_arr.split(',').join('|') + ')';
            }
            table.search(searchString, true).draw(true);
        });



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
