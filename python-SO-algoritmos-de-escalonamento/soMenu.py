# Nome     : Antonio Sergio Ferreira Pacheco
# Matricula: 20161148060007

# ta - turn around
# te - tempo espera
# tc - tempo cpu
# tta - tempo de turn around
# mte - media tempo de espera
# mta  - media tempo Turn Around
#
# como instalar pacote PrettyTable:
# pip install https://pypi.python.org/packages/source/P/PrettyTable/prettytable-0.7.2.tar.bz2

from prettytable import PrettyTable

def FIFO():

    print('FIFO ---------------------')
    n = 5
    print('Quantidade de Processos: 5')
    # n=int(input())

    print('Tempo de CPU: 5 10 3 4 7\n')
    tc = [5, 10, 3, 4, 7]
    # tp = [int(x)for x in input().split()]
    pr = [int(p) for p in range(n)]
    te = []
    ta = []
    mte = 0
    mta = 0
    te.insert(0, 0)
    ta.insert(0, tc[0])

    tx = []
    for x in range(n): tx.append(str(chr(ord('A') + x)))

    for i in range(1, len(tc)):
        #
        te.insert(i, te[i - 1] + tc[i - 1])
        ta.insert(i, te[i] + tc[i])
        mte += te[i]
        mta = ta[i]

    x = PrettyTable()
    x.field_names = ['Processo', 'Tempo CPU', 'Tempo Espera', 'Tempo Turn Around']

    for i in range(0, n):
        x.add_row([tx[i], tc[i], te[i], ta[i]])
    print(x)
    print("Tempo de Espera médio: " + str(mte / n))
    print("Tempo de Turn Around médio: " + str(mta / n))


def SJF():

    tc = []
    te = []
    pr = []
    tat = []
    mte = 0.0
    mtat = 0.0
    i = 0
    j = 0
    t = 0
    print('SJF ---------------------')
    print("Quantidade Processos: 5")
    # n = int(input())
    n = 5
    te = [0 for e in range(n)]
    tat = [0 for e in range(n)]

    # for i in range(n):
    #    print("[%d] Tempo de CPU :", i)
    #    tc[i] = input()
    #    p[i] = i

    tc = [5, 10, 3, 4, 7]
    pr = [0, 1, 2, 3, 4]

    tx = []
    for x in range(n): tx.append(str(chr(ord('A') + x)))


    # Ordenar os processos com menor tempo de CPU

    for i in range(n - 1):
        for j in range(n - i - 1):
            if tc[j] > tc[j + 1]:
                t = tc[j]
                tc[j] = tc[j + 1]
                tc[j + 1] = t

                t = pr[j]
                pr[j] = pr[j + 1]
                pr[j + 1] = t

    # calcula tempo de espera e turn around dos processos

    te[0] = 0
    tat[0] = tc[0]
    mtat = tat[0]

    for i in range(1, n):
        te[i] = te[i - 1] + tc[i - 1]
        tat[i] = tat[i - 1] + tc[i]
        mte += te[i]
        mtat += tat[i]

    x = PrettyTable()
    x.field_names = ["Processo", "Tempo CPU", "Tempo Espera", "Tempo Turn Around"]
    for i in range(n):
        x.add_row([tx[i], tc[i], te[i], tat[i]])

    x.sortby = "Processo"
    print(x)
    print('Tempo de Espera Médio: ', mte / n)
    print('Tempo de Turn Around Médio: ', mtat / n)


def RR():

    from prettytable import PrettyTable

    tc = []  # tempo cpu
    te = []  # tempo de espera
    tta = [] # tempo de turn around
    qi = []  # Quantidade de interrupções
    q = 0    # Quantum
    mte = 0
    mtta = 0
    i = 0
    j = 0
    t = 0

    print('RR ---------------------')
    print("Quantidade Processos: 5")
    # n = int(input())
    n = 5
    print("Quantum: 2")
    # q = int(input())
    q = 2

    tc = [0 for x in range(n)]
    te = [0 for x in range(n)]
    tta = [0 for x in range(n)]
    qi = [-1 for x in range(n)]
    acabou = [False for x in range(n)]

    print('Informe o Tempo de CPU: ')
    # tc = [int(x)for x in input().split()]
    tc = [5, 10, 3, 4, 7]
    print(tc)
    print("")
    pr = []
    for x in range(n): pr.append(str(chr(ord('A') + x)))
    restoTc = tc[:]

    def soma_te(atual):
        for i in range(n):
            if atual != i and not acabou[i]:
                # não soma processo atual
                # não soma processos que tenham acabado
                te[i] += 1

    fila = True
    while fila:
        for i in range(n):
            fila = True
            if not acabou[i]:
                for j in range(q):  # quantum
                    soma_te(i)
                    restoTc[i] -= 1

                    if restoTc[i] == 0:
                        acabou[i] = True
                        break
                qi[i] += 1
            else:
                fila = False

    for i in range(n):
        tta[i] = te[i] + tc[i]
        mtta += tta[i]
        mte += te[i]

    x = PrettyTable()
    x.field_names = ["Processo", "Tempo CPU", "Tempo Espera", "Tempo Turn Around", "Interrupções"]
    for i in range(n):
        x.add_row([pr[i], tc[i], te[i], tta[i], qi[i]])

    x.sortby = "Processo"
    print(x)
    print('Média Tempo de Espera: ', mte / n)
    print('Média Tempo de Turn around: ', mtta / n)




# MENU

while True:
    try:
        print ('\n\n')
        print('ALGORITMOS DE ESCALONAMENTO')
        print('---------------------------')
        print ('FIFO - First In First Out')
        print ('SJF - Shortest Job First')
        print ('RR - Round Robin')
        print('---------------------------')
        op = int(input("Opção? [1] [2] [3]: "))

        print("")

        if op not in [1, 2, 3]:
            print("Fim")
            break
        t = set()

        if op == 1: FIFO()
        if op == 2: SJF()
        if op == 3: RR()

    except ValueError:
        print("Obrigado !")
        break