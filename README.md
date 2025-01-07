# SkyBase - Airline management application using Spring Boot

Nume: Nitoi Antonio
Grupa: 406 - IS

# Descriere

Proiectul reprezintă o aplicație de management al resurselor pentru companii aeriene, ce permite gestionarea informațiilor despre angajații companiei, job-urile pe care lucrează aceștia și departamentele din care fac parte aceste job-uri, aeronavele companiei și zborurile pe care compania le efectuează. De asemenea, pot fi gestionate aeroporturile din care compania efectuează zboruri, împreună cu orașele si țările în care se află acestea. Zborurile au un aeroport din care pleacă si un aeroport destinație si se repeta periodic, fiind efectuate de diverse aeronave si echipaje, in funcție de ziua în care are loc zborul. Astfel, aplicația permite alocarea pe zboruri a aeronavelor și a echipajelor.

# Business Requirements

1. Sistemul trebuie sa permita gestionarea aeroporturilor, oraselor si tarilor din care compania aeriana efectueaza zboruri.
2. Sistemul trebuie sa permita gestionarea departamentelor companiei si a job-urilor din cadrul acestora.
3. Sistemul trebuie sa permita gestionarea angajatilor companiei.
4. Sistemul trebuie sa permita gestionarea aeronavelor pe care le detine compania.
5. Sistemul trebuie sa permita gestionarea zborurilor efectuate de companie.
6. Utilizatorul trebuie sa poata vizualiza angajatii disponibili intr-o anumita data / disponibilitatile unui angajat.
7. Utilizatorul trebuie sa poata vizualiza aeronavele disponibile intr-o anumita data / disponibilitatile unei aeronave.
8. Sistemul trebuie sa permita alocarea pe un zbor a unei aeronave si a unui echipaj doar daca acestea sunt disponibile in acea data, altfel sa afiseze o eroare.
9. Sistemul trebuie sa permita alocarea pe un zbor a unei aeronave doar daca are autonomia necesara pentru acel zbor si a unui angajat doar daca face parte din departamentele de echipaj de zbor sau personal de cabina.
10. Sistemul trebuie sa permita vizualizarea alocarilor existente ale angajatilor si aeronavelor.

# Features pentru stadiul de MVP

1. CRUD pentru aeroporturi, orase, tari si zboruri.
2. CRUD pentru departamente si job-uri.
3. CRUD pentru resurse (angajati si aeronave).
4. Identificarea si vizualizarea disponibilitatilor angajatilor si aeronavelor.
5. CRUD pentru alocari si validarea disponibilitatii si potrivirii resurselor inainte de a salva alocarea acestora pe un zbor.
