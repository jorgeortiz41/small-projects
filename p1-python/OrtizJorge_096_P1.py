##!/usr/bin/python
# Filename: p1.py

##### ADD YOUR NAME, Student ID, and Section number #######
# NAME: Jorge Ortiz
# STUDENT ID: 802186893
# SECTION:096
###########################################################
#COMPLETE THE FUNCTIONS TO PERFORM THE REQUIRED TASKS CORRECTLY
#AND DEFINE ADDITIONAL FUNCTIONS IF NEEDED

###########  ADD YOUR CODE BELOW WHERE NECESSARY ###############################
#This function validates if a file is found for the entered name
def is_file(filename):
    try:
        fh=open(filename,'r')
        fh.close()
        return True
    except FileNotFoundError:
        return False

#This function prints the menu
def print_program_menu():
    print("\n")
    print("Welcome to payment calculator. Please, choose an option:")
    print("1. Employees payment (will create a employees_payment.txt file)")
    print("2. Employee name with maximum number of work hours")
    print("3. Employee name with minimum number of work hours")
    print("4. Employee name with maximum rate")
    print("5. Employee name with minimum rate")
    print("6. Exit")

#This function allows to verify the entered option
def identify_option(option):
    if option.isdigit() :  # Verify if this is a number
        numeric_option = int(option)
        # check if in range
        if numeric_option >= 1 and numeric_option <= 6:
            return numeric_option
        else:
            return -1 # invalid option
    else:
        return -1 # invalid option

# DEFINE THE BODY OF THE FUNCTION TO CALCULATE THE PAYMENT OF AN EMPLOYEE
#def calculate_payment(hrs,rate):

    #WRITE YOUR CODE HERE TO CALCULATE THE PAY
def calculate_paymentf(hrs,rate):
    overtime = 0
    if hrs <= 40 or rate > 50:
        pay = hrs * rate
        return pay
    elif hrs > 40 and rate <= 50:
        pay = 40 * rate + ((hrs-40) * (rate * 1.5))
        return pay
    else:
        print('ERROR')



###############################################################################
#COMPLETE THE FUNCTION BELOW FOR ALL THE OPTIONS 1 TO 5 CALLING THE NEEDED FUNCTIONS
#This function processes all the options
def process_request(option):
    filename = input("Enter the name of the file to be processed: ")
    if is_file(filename):
        fhandle = open(filename,'r') #open file
    else:
        print("Illegal file name. Input file was not found")
        
    if (option == 1):
        
        paymentf = open('employees_payment.txt', 'w')
        for line in fhandle:
            if not line.startswith('time:'):
                x = line.strip()
                y = x.find(',')
                names = x[:y]
                numbers = x[y+1:]
                z = numbers.find(',')
                hrs = float(numbers[:z])
                rate = float(numbers[z+1:])
                calculate_paymentf(hrs,rate)
                paymentf.write(names + ": " + "$" + str(calculate_paymentf(hrs,rate)) + "\n")
        paymentf.close()
        print('A file employees_payment.txt containing the payment information has been created.')
                
    if (option == 2):
        largest = 0
        for line in fhandle:
            if not line.startswith('time:'):

                x = line.strip()
                y = x.find(',')
                names = x[:y]
                numbers = x[y+1:]
                z = numbers.find(',')
                hrs = float(numbers[:z])
                rate = float(numbers[z+1:])
                if hrs > largest:
                    largest = hrs
                    name = names
        print("The employee with the maximum number of work hours is: ",name,largest)
        
    if (option == 3):
        smallest = 0
        

        for line in fhandle:
            if not line.startswith('time:'):
                

                x = line.strip()
                y = x.find(',')
                names = x[:y]
                numbers = x[y+1:]
                z = numbers.find(',')
                hrs = float(numbers[:z])
                rate = float(numbers[z+1:])
                if smallest == 0:
                    name = names
                    smallest = hrs
                elif hrs < smallest:
                    name = names
                    smallest = hrs
        print("The employee with the minimum number of work hours is: ",name,smallest)
        
    if (option == 4):
        largest = 0
        
        for line in fhandle:
            if not line.startswith('time:'):

                x = line.strip()
                y = x.find(',')
                names = x[:y]
                numbers = x[y+1:]
                z = numbers.find(',')
                hrs = float(numbers[:z])
                rate = float(numbers[z+1:])
                if rate > largest:
                    largest = rate
                    name = names
        print("The employee with the maximum rate is: ",name,largest)

    if (option == 5):
        smallest = 0
        

        for line in fhandle:
            if not line.startswith('time:'):
                

                x = line.strip()
                y = x.find(',')
                names = x[:y]
                numbers = x[y+1:]
                z = numbers.find(',')
                hrs = float(numbers[:z])
                rate = float(numbers[z+1:])
                if smallest == 0:
                    name = names
                    smallest = rate
                elif rate < smallest:
                    name = names
                    smallest = rate
        print("The employee with the minimum rate is: ",name,smallest)
        
        
################################################################
#This function invokes all the necessary functions
def main():
    done = False
    while not done:
        print_program_menu()
        user_option = input("Enter option: ")
        option_info = identify_option(user_option)
        if option_info != -1:
            #option was valid
            if option_info == 6:
                done = True
                print( "Thanks for using the payment calculation program")
            else:
                process_request(option_info)
        else:
            #option invalid
            print("Invalid Option\n")

# The code below makes Python start from the main function
# whenever our program is invoked as a "standalone program"
# (as opposed to being imported as a module).
if __name__ == "__main__":
    main()
