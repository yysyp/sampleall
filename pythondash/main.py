import dash
import dash_core_components as dcc
#import dash_html_components as html
from dash import html
#from dash.dependencies import Input, Output
from dash import dcc
from dash.dependencies import Input, Output

app = dash.Dash(__name__)

app.layout = html.Div(children=[
    html.H1("Your title"),
    dcc.Graph(id='example-graph',
              figure={
                  'data': [
                      {'x': [1,2,3], 'y':[4,1,2], 'type': 'bar', 'name': 'SF'},
                      {'x': [1,2,3], 'y':[2,4,5], 'type': 'bar', 'name': 'NYC'},
                  ],
                  'layout': {
                      'title': 'column sample'
                  }
              }),
    html.Hr(),          
    html.H1('下拉选择'),
    html.Br(),
    dcc.Dropdown(
        id='mychoice',
        options=[
            {'label': '选项一', 'value': 1},
            {'label': '选项二', 'value': 2},
            {'label': '选项三', 'value': 3}
        ]
    ),
    html.P(id='myoption')
])

@app.callback([Output('myoption', 'children')],
              [Input('mychoice', 'value')])
def listen_to_choice(choice):
    return [str(choice)]

if __name__ == '__main__':
    app.run_server(debug=True)