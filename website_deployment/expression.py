def get_data(gene_name):
    import pandas as pd
    expression_directory = "//home//ranzha//website_deployment//Samples_for_FrontEnd//"
    cell_type_list=["BimmNaive", "Bmem","CD4all","CD8all","CD8eff","CD8unknown","DC","MonoC",
           "MonoNC","NKact","NKmat","Plasma"]


    total_expression_df=pd.DataFrame()
    expression_list=[]
    cell_type_cor_list=[]
    cell_type_for_gene_list=[]

    for cell_type in cell_type_list:
        expression_file = expression_directory+cell_type+"_residual_expressions.tsv"
        expression_file_df = pd.read_csv(expression_file, sep="\t", header=[0], index_col=None)
        header_list=(list(expression_file_df))
        if gene_name in header_list:
            expression_column=list(expression_file_df[gene_name])
            expression_list.extend(expression_column)
            cell_type_cor_list.extend(list([cell_type] * len(expression_column)))
            cell_type_for_gene_list.append(cell_type)



    zero_cell_type=(list(set(cell_type_list) - set(cell_type_for_gene_list)))
    zero_expression=[0] * len(zero_cell_type)

    expression_list.extend(zero_expression)
    cell_type_cor_list.extend(zero_cell_type)




    total_expression_df["Expression"]=expression_list
    total_expression_df["Cell type"]=cell_type_cor_list




    return (total_expression_df,cell_type_list)





def plot(gene_name):
    import pandas as pd
    import plotly.express as px
    from plotly.offline import plot
    import plotly

    import plotly.graph_objects as go
    total_expression_df,cell_type_list = get_data(gene_name)



    fig = go.Figure()


    for cell_type in cell_type_list:
        fig.add_trace(go.Violin(x=total_expression_df["Cell type"][total_expression_df["Cell type"]==cell_type],
                                y=total_expression_df["Expression"][total_expression_df["Cell type"]==cell_type],
                                name=cell_type,
                                box_visible=True,
                                meanline_visible=True
                               ))
    fig.update_layout(
        title={
            'text': "Expression level for gene "+gene_name,
            'y': 0.9,
            'x': 0.5,
            'xanchor': 'center',
            'yanchor': 'top'

        },

        yaxis_title="Expression level (log10)",

    )

    # plot(fig,
    #      include_plotlyjs=False,
    #      output_type='div')
    plotly.offline.plot(fig,filename="//home//ranzha//website_deployment//index.html",auto_open=False)
    #fig.write_html("./graph_folder/")









#
#
if __name__ == '__main__':
    import argparse
    parser = argparse.ArgumentParser()
    parser.add_argument('gene_name', type=str,help="please enter gene name for visualising expression level")
    args = parser.parse_args()
    gene_name=args.gene_name
#
    print('successfully getting arguments:',gene_name)
    plot(gene_name)
