# Nome     : Antonio Sergio Ferreira Pacheco
# Matricula: 20161148060007
#
#  GERENCIADOR DE MEMÓRIA

from random import randrange, uniform

# Carregar: Cinco processos, usando de 1 a 10 de espaços de momória
processo = [0 for x in range(5)]
base = [0 for x in range(5)]
limite = [0 for x in range(5)]
memo = [" " for x in range(51)]

# Atribui um valor aleatório de 1 a 10 para o tamanho do processo
for i in range(5): processo[i] = randrange(1, 11)
tx = []
for x in range(5):
    tx.append(str(chr(ord('A') + x)))
    # print(tx[x], p[x])

# Carrega no Espaco de Memória
m=1
for i in range(5):
    base[i] = m
    for x in range(processo[i]):
        memo[m]=tx[i]
        m+=1
    limite[i] = m-1

# Carregar
# 01( ) 02( )
# A[base=1 limite=3]

def imprime():
    print('Endereço de Memória e Conteudo')
    print('------------------------------------------------------------')
    for i in range(1,51):
        if (i % 10) == 0:
            print('%02d(%01s) ' % (i, memo[i]))
        else:
            print('%02d(%01s) ' % (i, memo[i]),  end='')
    print ('------------------------------------------------------------')
    for i in range(5):
        print('%s=%02d[base=%02d limite=%02d] ' % (tx[i],processo[i],base[i],limite[i]))
    print ('------------------------------------------------------------')

imprime()

while True:
    letra=input('Escolha do Processo[ABCDE]: ').upper()
    if letra in 'ABCDE':
       nrProc = ord(letra) - 65

       endereco=int(input('Escolha o Endereço: '))
       valor=input('Escolha o Valor: ')

       if endereco >= base[nrProc] and endereco <= limite[nrProc]:
           memo[endereco]=valor
           for w in range(50): print('')
           imprime()

       else:
           print('ERRO: O processo não tem permissão para gravar neste endereço')
           k=input('Pressione ENTER para continuar')
           for w in range(50): print('')
           imprime()

