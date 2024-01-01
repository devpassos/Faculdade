
def plotar_barras():
    import pandas as pd
    import matplotlib.pyplot as plt
    import numpy as np

    # Carregar o dataset
    dados_covid = pd.read_csv('./COVID-19 Dataset/country_wise_latest_consolidated.csv', sep=';')

    # Determinar a coluna de continente e de número de infecções
    continentes = dados_covid['Continente']
    infeccoes = dados_covid['Confirmed']

    # Criar uma lista de cores para as barras
    cores = ['steelblue', 'salmon', 'limegreen', 'gold', 'purple', 'cyan', 'orange', 'pink']

    # Criar um array de índices para as barras
    indices = np.arange(len(continentes))

    # Plotar o gráfico de barras
    plt.figure(figsize=(10, 6))
    plt.bar(indices, infeccoes, color=cores)

    # Personalizar o eixo x
    plt.xticks(indices, continentes, rotation=45)
    plt.xlabel('Infecções por Covid Em Cada Continente.\n Dados de: 01/2020 a 07/2020')

    # Personalizar o eixo y
    plt.ylabel('Número de Infecções')
    plt.yscale('log')  # Usar escala logarítmica para melhor visualização dos dados

    # Título do gráfico
    plt.title('Número de Infecções por COVID-19')

    # Exibir os valores de cada barra acima delas
    for i, v in enumerate(infeccoes):
        plt.text(i, v, str(v), ha='center', va='bottom')

    # Exibir o gráfico
    plt.tight_layout()
    plt.show()

    #Código PDF UNIDADE I
    '''
    1. import matplotlib.pyplot as plt; plt.rcdefaults()
    2. import numpy as np
    3. musicas = ('Lib Provisória', 'Sentadão', 'Combatchy', 'Surtada', 'Cheirosa')
    4. indice = np.arange(len(musicas))
    5. acessos = [1068254,866216,849895,763652,758198]
    6. plt.bar(indice, acessos)
    7. plt.xticks(indice, musicas)
    8. plt.ylabel('Acessos')
    9. plt.title('Ranking do Spotify 31.dez.2019')
    10. plt.show()

    '''

def plotar_serie_temporal():
    import pandas as pd
    import matplotlib.pyplot as plt

    # Carregar o arquivo CSV
    df = pd.read_csv('./COVID-19 Dataset/day_wise.csv')

    # Converter a coluna 'Date' para o formato de data
    df['Date'] = pd.to_datetime(df['Date'])

    # Selecionar as colunas
    df = df[['Date', 'Confirmed', 'Deaths']]

    # Calcular a taxa de mortalidade (relação entre mortes e casos confirmados)
    df['Mortality Rate'] = df['Deaths'] / df['Confirmed']

    # Criar o gráfico de série temporal
    plt.figure(figsize=(10, 6))
    plt.plot(df['Date'], df['Confirmed'], label='Casos Confirmados')
    plt.plot(df['Date'], df['Deaths'], label='Mortes')
    plt.plot(df['Date'], df['Mortality Rate'], label='Taxa de Mortalidade')
    plt.xlabel('Data')
    plt.ylabel('Número de casos e mortes')
    plt.title('Casos confirmados, Mortes e Taxa de Mortalidade ao longo do tempo')
    plt.legend()
    plt.xticks(rotation=45)
    plt.ticklabel_format(style='plain', axis='y')  # Desativar a formatação de escala

    plt.show()

    # Código PDF UNIDADE II
    '''
    1. from pandas import read_csv
    2. from matplotlib import pyplot
    3. series = read_csv(r"<informe_sua_pasta>\\USD_BRL_hist.csv", header=0, index_col=0, parse_dates=True, squeeze=True)
    4. series.plot()
    5. pyplot.show()
    '''

def plotar_threemap():
    import pandas as pd
    import plotly.graph_objects as go

    # Carregar o arquivo CSV
    df = pd.read_csv('./COVID-19 Dataset/country_wise_latest_consolidated.csv', sep=';')

    # Filtrar as colunas relevantes
    df = df[['Continente', 'Confirmed', 'Deaths']]

    # Calcular a taxa de mortalidade (relação entre mortes e casos confirmados)
    df['Mortality Rate'] = (df['Deaths'] / df['Confirmed']) * 100

    # Criar uma lista de dicionários para cada quadrante
    data = []

    # Quadrante de casos confirmados
    data.append(dict(
        type='treemap',
        labels=df['Continente'],
        parents=[''] * len(df),
        values=df['Confirmed'],
        text=df['Continente'] + '<br>Casos Confirmados: ' + df['Confirmed'].astype(str),
        hovertemplate='Casos Confirmados: %{text}<extra></extra>'
    ))

    # Quadrante de mortes
    data.append(dict(
        type='treemap',
        labels=df['Continente'],
        parents=[''] * len(df),
        values=df['Deaths'],
        text=df['Continente'] + '<br>Mortes: ' + df['Deaths'].astype(str),
        hovertemplate='Mortes: %{text}<extra></extra>'
    ))

    # Quadrante de taxa de mortalidade
    data.append(dict(
        type='treemap',
        labels=df['Continente'],
        parents=df['Continente'],
        values=df['Mortality Rate'],
        text=df['Continente'] + '<br>Taxa de Mortalidade: ' + df['Mortality Rate'].apply(lambda x: f'{x:.2f}%'),
        hovertemplate='Taxa de Mortalidade: %{text}<extra></extra>'
    ))

    # Criar o layout do gráfico
    layout = go.Layout(
        title='Threemap - Casos Confirmados, Mortes e Taxa de Mortalidade por Continente',
        height=600
    )

    # Criar a figura com os dados e o layout
    fig = go.Figure(data=data, layout=layout)

    # Exibir o gráfico
    fig.show()

# Press the green button in the gutter to run the script.
if __name__ == '__main__':

    #plotar_barras()
    #plotar_serie_temporal()
    plotar_threemap()


# See PyCharm help at https://www.jetbrains.com/help/pycharm/
