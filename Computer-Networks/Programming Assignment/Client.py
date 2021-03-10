from socket import socket
import re

def assignment_1():

    inputData = str(input("Enter IP address & Port#: ")).split(' ')     # Taking input of IP adress & Port #
    try:
        assert len(inputData) == 2                                      # Checking if both IP adress & port number were given
    except:
        print("There was an error in the input.\n[Process Completed]")
        return

    hostIP = inputData[0]
    hostPort = int(inputData[1])
    del inputData

    try:
        clientSocket = socket()                                     # Creating a socket, By defualt IPv4 & TCP
        clientSocket.connect((hostIP,hostPort))                     # Trying to create a connection 
        print("connection status: success")

        while True:
            command = input("Enter command:")                   

            if command == "POST":                                   # POST Command
                combinedMsg, msg = command + "\n", command
                while msg != '.':
                    print(f"client: {msg}")                         # Outputting the message in the required form
                    msg = input() 
                    combinedMsg += msg + "\n"
                clientSocket.send(bytes(combinedMsg,"utf-8"))       # Sending the combined POST string to server
                serverSignal = clientSocket.recv(256).decode()      # Receiving Acknowledgment from Server
                print(serverSignal)

            elif command == "READ":                                 # READ command
                clientSocket.send(bytes(command+'\n',"utf-8"))  
                lastMsg = re.compile(r"\.\B")                       # telling code how the last message should look like
                while True:
                    rcvMsg = clientSocket.recv(1024).decode() 
                    if lastMsg.match(rcvMsg) is not None:           # Checking if the message received is the last message
                        break
                    print(f"server: {rcvMsg}")                      # Printing the received message in the required form
                print("server: .")

            elif command == "QUIT":                                 # QUIT command
                clientSocket.send(bytes(command+'\n',"utf-8"))  
                serverSignal = clientSocket.recv(256).decode()      # Receiving Acknowledgment from Server
                print(f"server: {serverSignal}")                
                break

            else:
                clientSocket.send(bytes(command, "utf-8"))
                serverSignal = clientSocket.recv(512).decode()      # Receiving Acknowledgment from Server
                print(f"server: {serverSignal}")      

    except Exception as e:                                          # handling any exception that might occur during runtime
        errorMsg = str(e).split(']')[1]
        print(f"connection status:{errorMsg}")

    print("\n[Process completed]")


# Driver Code
if __name__ == "__main__":
    assignment_1()