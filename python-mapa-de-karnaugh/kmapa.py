# IFRN instituto Federal de Educação, Ciência e Tecnologia do Rio Grande do Norte
# Curso     : Tecnologia em Sistemas para Internet 
# Disciplina: Algoritimos 
#
# Projeto   : Mapa de Karnaugh 1.0 sob orientação do Prof. Valério
#             Método de simplificação gráfico criado por Edward Veitch (1952)
#             e aperfeiçoado pelo engenheiro de telecomunicações Maurice Karnaugh.

b2=["o---------", 
    "|  o------", 
    "|  |  o---", 
    "|  |  |  o"] 
b3=["o---------------", 
    "|  o------------",
    "|  |  o---------",
    "|  |  |  o------",
    "|  |  |  |  o---",
    "|  |  |  |  |  o"]
b4=["o---------------------",    
    "|  o------------------",   
    "|  |  o---------------",                                          
    "|  |  |  o------------",    
    "|  |  |  |  o---------",   
    "|  |  |  |  |  o------", 
    "|  |  |  |  |  |  o---",
    "|  |  |  |  |  |  |  o"]
    
def lx(var): 
    if var==2: return "|  |  |  |" 
    if var==3: return "|  |  |  |  |  |"
    if var==4: return "|  |  |  |  |  |  |  |"        

def topo_circuito(var): 
    if var==2:
        print("   _     _")           
        print("A  A  B  B")          
        lx(2)  
        lx(2)
        
    if var==3:
        print("   _     _     _")           
        print("A  A  B  B  C  C")          
        lx(3) 
        lx(3)
     
    if var==4:
        print("   _     _     _     _")           
        print("A  A  B  B  C  C  D  D")          
        lx(4) 
        lx(4)

def li(var, t):
    if var==2:     
        if      t=="A": return b2[0]+"----"
        elif    t=="a": return b2[1]+"----"
        elif    t=="B": return b2[2]+"----"
        elif    t=="b": return b2[3]+"----"
        
    if var==3:
        if      t=="A": return b3[0]+"----"
        elif    t=="a": return b3[1]+"----"
        elif    t=="B": return b3[2]+"----"
        elif    t=="b": return b3[3]+"----"
        elif    t=="C": return b3[4]+"----"
        elif    t=="c": return b3[5]+"----"
        
    if var==4:
        if      t=="A": return b4[0]+"----"
        elif    t=="a": return b4[1]+"----"
        elif    t=="B": return b4[2]+"----"
        elif    t=="b": return b4[3]+"----"
        elif    t=="C": return b4[4]+"----"
        elif    t=="c": return b4[5]+"----" 
        elif    t=="D": return b4[6]+"----"
        elif    t=="d": return b4[7]+"----"    

#Circuito OR
def lor(linha, ordem):
    texto=""
    
    # Dois termos AND 
    if  tamanho ==2: 
        if ordem == ultimo:         
            if   linha==1:      texto+="      |"
            elif linha==2:      texto+="      |    #1####"
            elif linha==3:      texto+="      +-----#2######"
            elif linha==4:      texto+="             #3######"
            elif linha==5:      texto+="              #4######--- "
            elif linha==6:      texto+="             #5######"
            elif linha==7:      texto+="------------#6######"
            elif linha==8:      texto+="           #7####"
            elif linha==9:      texto+=""
            elif linha==10:     texto+=""
        else: 
            if ordem==0: # Primeiro
                if   linha==7:  texto+="------+"
                elif linha==8:  texto+="      |"
                elif linha==9:  texto+="      |"
                elif linha==10: texto+="      |"
            
    # Tres termos AND
    if tamanho==3:  
        if ordem == ultimo: 
            if   linha==1:      texto+="   |  |"
            elif linha==2:      texto+="   |  |    #1####"
            elif linha==3:      texto+="   |  +-----#2######"
            elif linha==4:      texto+="   |         #3######"
            elif linha==5:      texto+="   +----------#4######--- "
            elif linha==6:      texto+="             #5######"
            elif linha==7:      texto+="------------#6######"
            elif linha==8:      texto+="           #7####"
            elif linha==9:      texto+=""                  
            elif linha==10:     texto+=""                  
        else: 
            if ordem==0:        # Primeiro                     
                if   linha==7:  texto+="------+"
                elif linha==8:  texto+="      |"
                elif linha==9:  texto+="      |"
                elif linha==10: texto+="      |"
            else:               # Do meio 
                if   linha==1:  texto+="      |"
                elif linha==2:  texto+="      |"
                elif linha==3:  texto+="      |"
                elif linha==4:  texto+="      |"
                elif linha==5:  texto+="      |"
                elif linha==6:  texto+="      |"
                elif linha==7:  texto+="---+  |"
                elif linha==8:  texto+="   |  |"
                elif linha==9:  texto+="   |  |"                  
                elif linha==10: texto+="   |  |"              
                
    # Quatro termos AND    
    if  tamanho==4:
        if  ordem == ultimo: 
            if linha==1:        texto+="|  |  |"
            if linha==2:        texto+="|  |  |    #1####"
            if linha==3:        texto+="|  |  +-----#2######"
            if linha==4:        texto+="|  +---------#3######"
            if linha==5:        texto+="|             #4######--- "
            if linha==6:        texto+="+------------#5######"
            if linha==7:        texto+="------------#6######"
            if linha==8:        texto+="           #7####"
            if linha==9:        texto+=""
            if linha==10:       texto+=""
        else:
            if ordem==0: # Primeiro
                if   linha==7:  texto+="------+"
                elif linha==8:  texto+="      |"
                elif linha==9:  texto+="      |"
                elif linha==10: texto+="      |"
                
            if ordem==1:  # Segundo 
                if   linha==1:  texto+="      |"
                elif linha==2:  texto+="      |"
                elif linha==3:  texto+="      |"
                elif linha==4:  texto+="      |"
                elif linha==5:  texto+="      |"
                elif linha==6:  texto+="      |"
                elif linha==7:  texto+="---+  |"
                elif linha==8:  texto+="   |  |"
                elif linha==9:  texto+="   |  |"
                elif linha==10: texto+="   |  |"
                
            if ordem==2:  # Terceiro
                if   linha==1:  texto+="   |  |"
                elif linha==2:  texto+="   |  |"
                elif linha==3:  texto+="   |  |"
                elif linha==4:  texto+="   |  |"
                elif linha==5:  texto+="   |  |"
                elif linha==6:  texto+="   |  |"
                elif linha==7:  texto+="+  |  |"
                elif linha==8:  texto+="|  |  |"
                elif linha==9:  texto+="|  |  |"
                elif linha==10: texto+="|  |  |"   
    return texto
    
def porta_AND(o, termo, var):  # (sequencia, termo, variaveis)
    
    if len(termo)==1: 
        print(lx(var)+          "                         "+lor(1,o))
        print(lx(var)+          "                         "+lor(2,o))
        print(lx(var)+          "                         "+lor(3,o))
        print(lx(var)+          "                         "+lor(4,o))
        print(lx(var)+          "                         "+lor(5,o))
        print(lx(var)+          "                         "+lor(6,o))
        print(li(var, termo[0])+    "---------------------"+lor(7,o))
        print(lx(var)+          "                         "+lor(8,o))
        print(lx(var)+          "                         "+lor(9,o))
        print(lx(var)+          "                         "+lor(10,o))

    if len(termo)==2: 
        
        print(lx(var)+          "                         "+lor(1,o))
        print(lx(var)+          "                         "+lor(2,o))
        print(lx(var)+          "                         "+lor(3,o))            
        print(lx(var)+          "    #1######             "+lor(4,o))                  
        print(li(var, termo[0])+    "#2########           "+lor(5,o))                
        print(lx(var)+          "    #3#########          "+lor(6,o))                
        print(lx(var)+          "    #4#########----------"+lor(7,o))           
        print(lx(var)+          "    #5#########          "+lor(8,o))    
        print(li(var, termo[1])+    "#6########           "+lor(9,o))     
        print(lx(var)+          "    #7######             "+lor(10,o))      

    if len(termo)==3:    
        
        print(lx(var)+          "                         "+lor(1,o))
        print(lx(var)+          "                         "+lor(2,o))
        print(lx(var)+          "                         "+lor(3,o))                                                      
        print(lx(var)+          "    #1######             "+lor(4,o))       
        print(li(var, termo[0])+    "#2########           "+lor(5,o))     
        print(lx(var)+          "    #3#########          "+lor(6,o))         
        print(li(var, termo[1])+    "#4#########----------"+lor(7,o))          
        print(lx(var)+          "    #5#########          "+lor(8,o))
        print(li(var, termo[2])+    "#6########           "+lor(9,o))     
        print(lx(var)+          "    #7######             "+lor(10,o))      
 
        
    if len(termo)==4:  
        
        print(lx(var)+          "                         "+lor(1,o))
        print(lx(var)+          "                         "+lor(2,o))
        print(lx(var)+          "                         "+lor(3,o))                             
        print(lx(var)+          "    #1######             "+lor(4,o))      
        print(li(var, termo[0])+    "#2########           "+lor(5,o))     
        print(li(var, termo[1])+    "#3#########          "+lor(6,o))
        print(lx(var)+          "    #4#########----------"+lor(7,o))
        print(li(var, termo[2])+    "#5#########          "+lor(8,o))
        print(li(var, termo[3])+    "#6########           "+lor(9,o))        
        print(lx(var)+          "    #7######             "+lor(10,o))     

# Obtem a tabela verdade conforme o numero de variaveis        
def tabela_verdade(var):
    m=[0 for i in range(var**2)]  
    ab="     A B "
    c="C "
    d="D"
    print("--------------")
    linha=2
    if  var==3: 
        ab+=c
    if  var==4: 
        linha=4
        ab=ab+c+d
    print(ab)
    print("--------------")

    tverdade=0
    if  var==3:  
        tverdade=8
    else:   
        tverdade=var**2
    
    # Tabela Verdade
    for i in range(tverdade):
        msg=""
        v="{:04d}".format(i)
        if  var==2:
            a=str("{:02d} ".format(  int(bin(i)[2:])    )   )
            msg+="{:02d}".format(i)+"  ["+a[0]+" "+a[1]
        elif  var==3: 
            a=str("{:03d} ".format(  int(bin(i)[2:])    )   )
            msg+="{:02d}".format(i)+"  ["+a[0]+" "+a[1]+" "+a[2]
        else:   
            a=str("{:04d} ".format(  int(bin(i)[2:])    )   )
            msg+="{:02d}".format(i)+"  ["+a[0]+" "+a[1]+" "+a[2]+" "+a[3]
        msg+="] = "
        m[i]=int(input(msg))

    print("--------------")
    
    if var==2:
        mapa[0][0]=m[0]
        mapa[0][1]=m[1]
        mapa[1][0]=m[2]
        mapa[1][1]=m[3]
        
    if  var == 3: 
        mapa[0][0]=m[0]
        mapa[0][1]=m[1]
        mapa[0][2]=m[3]
        mapa[0][3]=m[2]
        mapa[1][0]=m[4]
        mapa[1][1]=m[5]
        mapa[1][2]=m[7]
        mapa[1][3]=m[6]
        
    if  var == 4: 
        mapa[0][0]=m[0]
        mapa[0][1]=m[1]
        mapa[0][2]=m[3]
        mapa[0][3]=m[2]
            
        mapa[1][0]=m[4]
        mapa[1][1]=m[5]
        mapa[1][2]=m[7]
        mapa[1][3]=m[6]
            
        mapa[2][0]=m[12]
        mapa[2][1]=m[13]
        mapa[2][2]=m[15]
        mapa[2][3]=m[14]
            
        mapa[3][0]=m[8]
        mapa[3][1]=m[9]
        mapa[3][2]=m[11]
        mapa[3][3]=m[10]
    
def imprime_mapa(variaveis): 
    
    if  variaveis==2: 
        print("                     ")
        print("                     ")
        print("    Layout do Mapa   ")
        print("      b      B       ")
        print("    +-----+-----+    ")
        print(" a  |  0  |  1  |    ")
        print("    +-----+-----+    ")
        print(" A  |  2  |  3  |    ")
        print("    +-----+-----+    ")
        print("                     ")
        print("                     ")
        print("    Mapa de Karnaugh ")
        print("      b      B       ")
        print("    +-----+-----+    ")
        print(" a  |  "+str(mapa[0][0])+"  |  "+str(mapa[0][1])+"  |    ")
        print("    +-----+-----+    ")
        print(" A  |  "+str(mapa[1][0])+"  |  "+str(mapa[1][1])+"  |    ")
        print("    +-----+-----+    ")

    elif variaveis==3: 
        print("                               ")
        print("                               ")
        print("          Layout do Mapa       ")
        print("       bc    bC   BC    Bc     ")
        print("    +-----+-----+-----+-----+  ")
        print(" a  |  0  |  1  |  3  |  2  |  ")
        print("    +-----+-----+-----+-----+  ")
        print(" A  |  4  |  5  |  7  |  6  |  ")
        print("    +-----+-----+-----+-----+  ")
        print("                               ")
        print("                               ")
        print("         Mapa de Karnaugh      ")
        print("       bc    bC   BC     Bc    ")
        print("    +-----+-----+-----+-----+  ")
        print(" a  |  "+str(mapa[0][0])+"  |  "+str(mapa[0][1])+"  |  "+str(mapa[0][2])+"  |  "+str(mapa[0][3])+"  |  ")
        print("    +-----+-----+-----+-----+  ")
        print(" A  |  "+str(mapa[1][0])+"  |  "+str(mapa[1][1])+"  |  "+str(mapa[1][2])+"  |  "+str(mapa[1][3])+"  |  ")
        print("    +-----+-----+-----+-----+  ")
        print("                               ")
        print("                               ")

    elif variaveis==4: 
        print("                               ")
        print("    Layout do Mapa             ")
        print("       cd    cD    CD    Cd    ")
        print("    +-----+-----+-----+-----+  ")
        print(" ab |  0  |  1  |  3  |  2  |  ")
        print("    +-----+-----+-----+-----+  ")
        print(" aB |  4  |  5  |  7  |  6  |  ")
        print("    +-----+-----+-----+-----+  ")
        print(" AB | 12  | 13  | 15  | 14  |  ")
        print("    +-----+-----+-----+-----+  ")
        print(" Ab |  8  |  9  | 11  | 10  |  ")
        print("    +-----+-----+-----+-----+  ")
        print("                               ")
        print("                               ")
        print("    Mapda de Karnaugh          ")
        print("       cd   cD    CD    Cd     ")
        print("    +-----+-----+-----+-----+  ")
        print(" ab |  "+str(mapa[0][0])+"  |  "+str(mapa[0][1])+"  |  "+str(mapa[0][2])+"  |  "+str(mapa[0][3])+"  |  ")
        print("    +-----+-----+-----+-----+  ")
        print(" aB |  "+str(mapa[1][0])+"  |  "+str(mapa[1][1])+"  |  "+str(mapa[1][2])+"  |  "+str(mapa[1][3])+"  |  ")
        print("    +-----+-----+-----+-----+  ")
        print(" AB |  "+str(mapa[2][0])+"  |  "+str(mapa[2][1])+"  |  "+str(mapa[2][2])+"  |  "+str(mapa[2][3])+"  |  ")
        print("    +-----+-----+-----+-----+  ")
        print(" Ab |  "+str(mapa[3][0])+"  |  "+str(mapa[3][1])+"  |  "+str(mapa[3][2])+"  |  "+str(mapa[3][3])+"  |  ")
        print("    +-----+-----+-----+-----+  ")

def direita(x, y):
    try:               
        return x, y+1, mapa[x][y+1]
    except IndexError: 
        return x, 0, mapa[x][0]

def esquerda(x, y):
    
    erro=0
    if y!=0: erro=y-1
    else:    erro=len(mapa[x])-1
    
    return x, erro, mapa[x][y-1]
    
def baixo(x, y):
    try:               
        return x+1, y, mapa[x+1][y]
    except IndexError: 
        return   0, y, mapa[0][y]

# procura os grupos no mapa de Karnaugh
def procura_grupos(x, y):
    resultado = []
    r = []
    if mapa[x][y] == 1:
        r.append([(x,y),])
        resultado = r
    else: 
        return resultado
        
    par=False 
    
    r = []
    kb = baixo(x, y)
    if mapa[x][y] == 1 and  kb[2] > 0:       # par com o bit abaixo   
        par = True
        r.append([(x,y), (kb[0], kb[1])])

    kd = direita(x, y)
    if mapa[x][y] == 1 and kd[2] > 0:        # par com o bit a direita
        par = True
        r.append([(x,y), (kd[0], kd[1])])

    if  par: 
        resultado = r
    else: 
        return resultado
        
    if  variaveis == 2: return resultado    

    quadra=True                            
    
    r  = []
    p  = direita (kb[0], kb[1])
    g4 = [(x,y), (kb[0], kb[1]), (kd[0], kd[1]), (p[0], p[1])]
    
    for w in g4:                                                # Formou uma quadra
        if mapa[w[0]][w[1]] == 0: quadra=False      
        
    if quadra:
        r.append(g4)
        resultado = r

#---------  linha de 4

    posicao  = (x, y)
    linha_g4 = [posicao]
    ligado   = True 
    
    for k in range(3):                              # Direita 3 casas
        p = direita(posicao[0], posicao[1]) 
        posicao = (p[0], p[1])
        linha_g4.append(posicao)
        if mapa[p[0]][p[1]]==0: ligado=False
        
    if  mapa[x][y]>0 and ligado:   
        quadra=True
        r.append(linha_g4)
        
#---------  coluna de 4      
        
    if variaveis == 4:                               # Bloco de 4 coluna
        posicao   = (x, y)
        coluna_g4 = [posicao]
        ligado    = True  
        
        for k in range(3): 
            p = baixo(posicao[0], posicao[1])
            posicao = (p[0], p[1])
            coluna_g4.append(posicao)
            if mapa[p[0]][p[1]] == 0: ligado=False 
               
        if mapa[x][y] > 0 and ligado:
            quadra = True
            r.append(coluna_g4)
    if quadra: 
        resultado = r
    else: 
        return resultado

    if  variaveis < 4: return resultado
    
    r = []
    octeto=False                                        # Bloco 4 linha
    
    posicao = (x, y)
    linha_g8 = [posicao]
    
    for i in range(3): 
        p = direita(posicao[0], posicao[1])
        posicao = (p[0], p[1])
        linha_g8.append(posicao)
    
    p = baixo(posicao[0], posicao[1])
    posicao = (p[0], p[1])
    linha_g8.append(posicao)
   
    for k in range(3):      
        p = esquerda(posicao[0], posicao[1])
        posicao = (p[0], p[1])
        linha_g8.append(posicao)
   
    ligado=True    
    for w in linha_g8:
        if mapa[w[0]][w[1]] == 0: ligado=False                     
           
    if ligado:
        octeto = True
        r.append(linha_g8)


    posicao = (x, y)                            # Octeto 8 coluna
    coluna_g8 = [posicao]
    
    for k in range(6): 
        if   k in [0,4]:   p = direita (posicao[0], posicao[1])
        elif k in [1,3,5]: p = baixo   (posicao[0], posicao[1])
        elif k in [2,6]:   p = esquerda(posicao[0], posicao[1])
        posicao = (p[0], p[1])
        coluna_g8.append(posicao)
    
    ligado=True    
    for w in coluna_g8:
        if mapa[w[0]][w[1]] == 0: ligado=False                    # Octeto
           
    if ligado:
        octeto = True
        r.append(coluna_g8)

    if octeto: 
        resultado = r

    return resultado
    
def contido(lista, subconjunto):  
    for sub in subconjunto:
        if sub in lista:    
            continue
        else:    
            return False
    return True 
    
# Retorna a Função lógica simplificada        
def funcao_logica(variaveis):
    global resultado
    for x in range(len(mapa)):                  # Linha
        for y in range(len(mapa[0])):           # Coluna
            for grupo in procura_grupos(x, y):  # Par, Quadra, Octeto
                novo_termo=True
                
                for a, b in enumerate(resultado_grupo):  # Elimina repetidos
                    if  contido(grupo, b): 
                        resultado_grupo[a]=grupo
                        novo_termo=False
                        break
                    
                    if  contido(b, grupo):
                        novo_termo=False
                        break
                   
                if  novo_termo:  
                    resultado_grupo.append(grupo)
                    #print("GRUPO=%s RESULTADO=%s" % (str(grupo), str(resultado_grupo)))
                    
    # Identifica os agrupamentos do mapa e transforma em letras
    for item in resultado_grupo:
        resultado = ""
        for i in sorted(t.keys()): 
            if  set(item).issubset(set(t[i])): 
                resultado += i
        
        for j in ["Aa", "Bb", "Cc", "Dd"]:
            resultado = resultado.replace(j, "")

        termos.append(resultado)
         
    termos_set=set(termos)
    ln=len(termos_set)
    resultado=""
    for j, k in enumerate(termos_set):
        if  j+1<ln: resultado+=k+"  +  "
        else:       resultado+=k  
        
    # Imprime a Função Lógica
    print("------------------------")
    resultado = "F({}) = {}".format(var, resultado)
    print("FUNÇÃO LÓGICA ", resultado)
    print("------------------------")
    
    return termos
    
# Menu
print("##########################################")    
print("###   M a p a   d e   K a r n a u g h  ###")
print("##########################################")
print("")
while True: 
    try:
        variaveis=int(input("Variaveis? [2] [3] [4]: "))
        print("")
        
        if  variaveis not in [2,3,4]: 
            print("Fim")
            break
        t               = set() 
        grupo           = []
        resultado_grupo = []
        termos          = []
        resultado       = ""
         
        if  variaveis==2: 
            
            t = {"A":{(1, 0), (1, 1)},
                 "a":{(0, 0), (0, 1)},
                 "B":{(0, 1), (1, 1)},
                 "b":{(0, 0), (1, 0)}}
                 
            mapa        = [[ 0, 0],     # 00 01
                           [ 0, 0]]     # 10 11 
            var         = "A, B"
    
        elif variaveis==3: 
             
            t={"A":{(1, 0), (1, 1), (1, 2), (1, 3)},    #       00    01   11   10
               "a":{(0, 0), (0, 1), (0, 2), (0, 3)},    #       ab    bC   BC   Bc 
               "B":{(0, 2), (0, 3), (1, 2), (1, 3)},    # 0  A' 000   001  011  010        
               "b":{(0, 0), (0, 1), (1, 0), (1, 1)},    # 1  A  100   101  111  110 
               "C":{(0, 1), (0, 2), (1, 1), (1, 2)},
               "c":{(0, 0), (1, 0), (0, 3), (1, 3)}}
    
            mapa       =[[ 1, 0, 0, 1],                 # 00 01 02 03
                         [ 1, 0, 0, 1]]                 # 10 11 12 13
            var        = "A, B, C"
             
        elif variaveis==4: 
            
            t= {"A":{(2, 0), (2, 1), (2, 2), (2, 3), (3, 0), (3, 1), (3, 2), (3, 3)},            
                "a":{(0, 0), (0, 1), (0, 2), (0, 3), (1, 0), (1, 1), (1, 2), (1, 3)},    #         cd    cD    CD    Cd 
                "B":{(1, 0), (1, 1), (1, 2), (1, 3), (2, 0), (2, 1), (2, 2), (2, 3)},    # 0  ab   0000  0001  0011  0010  
                "b":{(0, 0), (0, 1), (0, 2), (0, 3), (3, 0), (3, 1), (3, 2), (3, 3)},    # 1  aB   0100  0101  0111  0110
                "C":{(0, 2), (1, 2), (2, 2), (3, 2), (0, 3), (1, 3), (2, 3), (3, 3)},    #    AB   1100  1101  1111  1110 
                "c":{(0, 0), (1, 0), (2, 0), (3, 0), (0, 1), (1, 1), (2, 1), (3, 1)},    #    Ab   1000  1001  1011  1010
                "D":{(0, 1), (1, 1), (2, 1), (3, 1), (0, 2), (1, 2), (2, 2), (3, 2)},
                "d":{(0, 0), (1, 0), (2, 0), (3, 0), (0, 3), (1, 3), (2, 3), (3, 3)}}
    
            mapa        = [[ 1, 0, 0, 1],  # 00 01 02 03
                           [ 1, 0, 0, 1],  # 10 11 12 13
                           [ 1, 0, 0, 1],  # 20 21 22 23
                           [ 1, 0, 0, 1]]  # 30 31 32 33
                           
            var         = "A, B, C, D"
             
        print("Tabela Verdade")
    
        # Obtem a Tabela Verdade
        tabela_verdade(variaveis)
        
        # Imprime o Mapa de Karnaugh
        imprime_mapa(variaveis)
        
        # Imprime a função lógica simplificada
        funcao_logica(variaveis) 
        
        # Imprime o circuito
        tamanho =len(termos)       # Quantidade de termos
        ultimo  =(len(termos)-1)   # Ultimo termo        
        topo_circuito(variaveis)      
    
        for i, item in enumerate(termos):
            porta_AND(i,item,variaveis)
       
        # Fim    
        break 
    
    except ValueError:
        print("Obrigado !")
        break
