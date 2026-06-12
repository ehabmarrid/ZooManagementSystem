Project name: Zoo Management System -
An intuitive program allowing the user to manage his own Zoo from animal-care (feeding & monitoring) to commercial-use (ticket sales and entries management & staff).

Functions/Features:
1) Monitoring the capacity of each section of the Zoo.
2) Distinction between animals each with their own breeds and needs.
3) Managing the staff of the Zoo and their Data (name, age , contact info, etc.)
4) Sales made easier with functions allowing to monitor, print, sale & cancel tickets.
5) Using Observer Design for Promotion Management sys.
-----------------------------------------------------------------------------------------
Updated Functions/Features:

1) Using singleton design pattern to ensure that one database for workers being created.
2) Changing the tickets using system, Tickets will be divided to Two types:
    A) The ones that can be used one time only - those can be cancelled.
    B) The annual Tickets Can't be cancelled.
3) updated a message about the usage of the tickets after every purchase!  -not done
4) Added a new list for used Tickets containing every entrance.
5) Updated PurchasedDate statues after buying a ticket.
-----------------------------------------------------------------------------------------
IMPORTANT INFO:
    Visitor Management System credentials are configured outside the code:
                        ZOO_ADMIN_USERNAME=Admin
                        ZOO_ADMIN_PASSWORD=<your-password>
    Database credentials are configured with:
                        ZOO_DB_URL, ZOO_DB_USER, ZOO_DB_PASSWORD
