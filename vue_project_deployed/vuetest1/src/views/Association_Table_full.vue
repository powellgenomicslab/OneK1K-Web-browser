<template>
    <div class="w3-container"  style="margin-left:300px;margin-top:43px;">
        <div class="w3-row-padding w3-margin-bottom">
            <div class="w3-quarter">
                <div class="w3-container w3-red w3-padding-16">
                    <div class="w3-left"><i class="fa fa-home w3-xxxlarge"></i></div>
                    <div class="w3-clear"></div>
                    <router-link :to="{path:'/'}" @click.native="refreshrefresh"> <h5>Home</h5> </router-link>
                </div>
            </div>

            <div class="w3-quarter">
                <div class="w3-container w3-blue w3-padding-16">
                    <div class="w3-left"><i class="fa fa-table w3-xxxlarge"></i></div>
                    <div class="w3-clear"></div>
                    <router-link :to="{path:'/eQTL_table'}" @click.native="refreshrefresh"><h5>eQTL</h5></router-link>
                </div>
            </div>

            <div class="w3-quarter">
                <div class="w3-container w3-teal w3-padding-16">
                    <div class="w3-left"><i class="fa fa-line-chart w3-xxxlarge"></i></div>
                    <div class="w3-clear"></div>
                    <router-link :to="{path:'/association_table'}" @click.native="refreshrefresh"><h5>Association </h5></router-link>
                </div>
            </div>

            <div class="w3-quarter">
                <div class="w3-container w3-orange w3-text-white w3-padding-16">
                    <div class="w3-left"><i class="fa fa-pie-chart w3-xxxlarge"></i></div>
                    <div class="w3-clear"></div>
                    <router-link :to="{path:'/expression'}" @click.native="refreshrefresh"> <h5>Expression Graph</h5> </router-link>
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
        </div>

        <table id="association-table-full" class=" display center-all" style="width:100%">
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
            refreshrefresh(){
                console.log("here")
                location.reload()},

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

    $(document).ready(function() {
        var table = $('#association-table-full').DataTable( {
            "ajax": {
                "url":"http://10.214.132.61:8181/association_table",

                "dataSrc": ""},


            initComplete: function () {
                this.api().columns([0]).every( function () {
                    var column = this;
                    // var table = $('#association-table-full').DataTable();

                    var select = $('<select id="celltype"><option default>All cell types</option></select>')
                        .insertBefore( $ (table.table().container()) )
                        .on( 'change', function () {
                            $('#association-table-full').DataTable().draw();
                        } );

                    column.data().unique().sort().each( function ( d, j ) {
                        select.append( '<option value="'+d+'">'+d+'</option>' );
                    } );
                } );
            },


            "oLanguage": {
                "sSearch": "SNP or Gene Search"

            },


            language: {
                searchPlaceholder: "e.g. ARSA or rs8142033"
            },




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
            "destroy": true,
            "retrieve": true

        } );




        $('.dataTables_filter').addClass('pull-left clearfix');

        $('#mySearch').on('keyup redraw', function() {
            table.search($('#mySearch').val(), true,true).draw(true);
        });
        $('#search').on("click redraw", function() {
            console.log(searchString)

            table.columns(3).search(searchString, true,true).draw(true);
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
