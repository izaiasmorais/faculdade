import streamlit as st
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np


def calculate_histogram(nk):
    size = 64 * 64
    pr = [round(i / size, 2) for i in nk]

    sk = np.cumsum(pr) * 7

    ps = np.round(sk).astype(int)

    nsk = [0, 0, 0, 0, 0, 0, 0, 0]
    for i in range(8):
        nsk[ps[i]] += nk[i]

    return pr, sk, ps, nsk


def plot_histogram(pr, sk, nsk):
    fig, (ax1, ax2) = plt.subplots(2, 1, figsize=(8, 10))

    rk = [0, 1, 2, 3, 4, 5, 6, 7]

    ax1.scatter(rk, pr, color="black")
    ax1.set_ylabel("pr(rk)")
    ax1.set_xlabel("rk")
    ax1.set_title("Histograma original")
    ax1.grid(True)

    ax2.scatter(rk, nsk, color="black")
    ax2.set_ylabel("nsk")
    ax2.set_xlabel("rk")
    ax2.set_title("Histograma equalizado")
    ax2.grid(True)

    plt.subplots_adjust(hspace=0.5)

    st.pyplot(fig)


def main():
    st.title("Histograma de Imagem Digital")

    st.write("Digite os valores de nk para calcular os histogramas:")

    nk_input = [1200, 1024, 980, 504, 0, 388, 0, 0]

    pr, sk, ps, nsk = calculate_histogram(nk_input)

    st.write("Tabela de Resultados:")
    df = pd.DataFrame({"nk": nk_input, "pr(nk)": pr, "trk": sk, "ps": ps, "nkeq": nsk})
    st.write(df)

    plot_histogram(pr, sk, nsk)


if __name__ == "__main__":
    main()
