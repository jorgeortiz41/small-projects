"""This program reads DNA sequences from an input file and finds the
consensus sequence.  An output file is also created to store the
counts per column, so as to validate the consensus.
Add the corresponding code to accomplish the requested tasks
"""



##### ADD YOUR NAME, Student ID, and Section number #######
# NAME: Walbert García Cruz
# STUDENT ID:802-18-7134
# SECTION:086
###########################################################


def load_data(fileName):
    #Read DNA sequences from file and return them in a list.
    # Assume the file to be open exist

    dataList = list()
    fhandle = open(fileName, 'r')
    for lines in fhandle:
        if not lines.startswith('>'):
            hope = lines.strip()
            dataList.append(hope)

    # Use dataList to save the the all data from the file 
    # If file opens successfully, loop over the contents and store sequences in list.
    # Skip description lines (lines that start with ">").




    #print(dataList)
    fhandle.close()
    return dataList


def count_nucl_freq(dataList):
    """Count the occurrences of characters by column."""
    countStruct = []
    for id, n in enumerate(["A", "C", 'G', "T"]):
        countStruct.append([])
        countStruct[id].append(n)
        for i in range(len(dataList[0])):
            total = 0
            for dna in dataList:
                if dna[i] == n:
                    total = total + 1
            countStruct[id].append(total)

    # Indexed by columns (List of what?)
    # Loop over the sequences in dataList to count the nucleotides
    # We'll need a nested loop to process every character in every sequence.
    # Recommend: Use outer loop for columns (characters) and inner loop for
    # rows (sequences), since countStruct only cares about the characters (not the seqs).

    return countStruct
    

def find_consensus(countData):
    """Return the consensus sequence according to highest-occuring nucleotides"""
    consensusString = ""
    posicioncolmna = 1
    nucleotido = None
    lst = []
    for lists in countData:
        lst.append(lists)
    for char in range(1, len(lst[0])):
        masgrande = -1000000
        for valores in countData:
            if posicioncolmna != len(valores):
                try:
                    if valores[posicioncolmna] > masgrande:
                        nucleotido = valores[0]
                        masgrande = valores[posicioncolmna]
                        #print('Nucleotido más grande es', nucleotido, "con valor de", masgrande, 'en pos', posicioncolmna)
                except:
                    break
        consensusString = consensusString + nucleotido
        posicioncolmna += 1
    print(consensusString)


    # Loop here to find highest-occuring nucleotide in each column
    # and concatenate them into consensusString

    return consensusString


def process_results(countData, outFilename):
    """Print consensus to screen and store results in output file."""
    consensus = find_consensus(countData)

    OUTPUTFILE = open('DNAOutput.txt', 'w')
    for i in range(1,len(countData[0])):

        OUTPUTFILE.write('Pos'+ str(i) + ':' + 'A' + ":" + str(countData[0][i]) + " " + ' '+ "C" + ':' + str(countData[1][i]) + " " + "G" + ':' + str(countData[2][i]) + " " + "T" + ':' + str(countData[3][i])+'\n')

    OUTPUTFILE.write('Conensus = '+ str(consensus) +'\n')
    '''
    OUTPUTFILE.write('Pos 1:' + 'A' + ":" + str(countData[0][1]) + " " + ' '+ "C" + ':' + str(countData[1][1]) + " " + "G" + ':' + str(countData[2][1]) + " " + "T" + ':' + str(countData[3][1])+'\n')

    OUTPUTFILE.write('Pos 2:' + 'A' + ":" + str(countData[0][2]) + " " + ' ' + "C" + ':' + str(countData[1][2]) + " " + "G" + ':' + str(countData[2][2]) + " " + "T" + ':' + str(countData[3][2])+'\n')

    OUTPUTFILE.write('Pos 3:' + 'A' + ":" + str(countData[0][3]) + " " + ' ' + "C" + ':' + str(countData[1][3]) + " " + "G" + ':' + str(countData[2][3]) + " " + "T" + ':' + str(countData[3][3])+'\n')

    OUTPUTFILE.write('Pos 4:' + 'A' + ":" + str(countData[0][4]) + " " + ' ' + "C" + ':' + str(countData[1][4]) + " " + "G" + ':' + str(countData[2][4]) + " " + "T" + ':' + str(countData[3][4])+'\n')

    OUTPUTFILE.write('Pos 5:' + 'A' + ":" + str(countData[0][5]) + " " + ' ' + "C" + ':' + str(countData[1][5]) + " " + "G" + ':' + str(countData[2][5]) + " " + "T" + ':' + str(countData[3][5])+'\n')

    OUTPUTFILE.write('Pos 6:' + 'A' + ":" + str(countData[0][6]) + " " + ' ' + "C" + ':' + str(countData[1][6]) + " " + "G" + ':' + str(countData[2][6]) + " " + "T" + ':' + str(countData[3][6])+'\n')

    OUTPUTFILE.write('Pos 7:' + 'A' + ":" + str(countData[0][7]) + " " + ' ' + "C" + ':' + str(countData[1][7]) + " " + "G" + ':' + str(countData[2][7]) + " " + "T" + ':' + str(countData[3][7])+'\n')

    OUTPUTFILE.write('Pos 8:' + 'A' + ":" + str(countData[0][8]) + " " + ' ' + "C" + ':' + str(countData[1][8]) + " " + "G" + ':' + str(countData[2][8]) + " " + "T" + ':' + str(countData[3][8])+'\n')

    OUTPUTFILE.write('Pos 9:' + 'A' + ":" + str(countData[0][9]) + " " + ' ' + "C" + ':' + str(countData[1][9]) + " " + "G" + ':' + str(countData[2][9]) + " " + "T" + ':' + str(countData[3][9])+'\n')

    OUTPUTFILE.write('Pos 10:' + 'A' + ":" + str(countData[0][10]) + " " + ' ' + "C" + ':' + str(countData[1][10]) + " " + "G" + ':' + str(countData[2][10]) + " " + "T" + ':' + str(countData[3][10])+'\n')
    '''
    OUTPUTFILE.close()
    # Now open the output file, and write the consensus string.
    # Then loop, to print nucleotide count in non-increasing order.
    # Each row in the output file (except the first one) should
    # have the count data for a column, in order of columns.
    print("DNAOutputfile has been created")

def main():

    # File name "constants". Assume the names of the files don change.
    try:
        INPUTFILE  = "DNAInput.fasta.txt"
    except:
        -1

    OUTPUTFILE = "DNAOutput.txt"


    seqList = load_data(INPUTFILE)

    countData = count_nucl_freq(seqList)

    process_results(countData, OUTPUTFILE)

# The code below makes Python start from the main function
# whenever our program is invoked as a "standalone program"
# (as opposed to being imported as a module).
if __name__ == "__main__":
    main()
