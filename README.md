# Bilabonnement
Et internt administrationssystem til håndtering af biludlejning, hvor du kan:
1. Oprette lejeaftaler
2. Registrere skader og skadesrapporter
3. Oprette nye brugere og biler
4. Se dashboard for antal udlejet biler samt prisen på dem

## Brug programmet online
Brug dette link: [projekt3gruppe4-bcf6h3cgdygqb8dw.germanywestcentral-01.azurewebsites.net](https://projekt3gruppe4-bcf6h3cgdygqb8dw.germanywestcentral-01.azurewebsites.net)

**Demo login:**
- Brugernavn: `demo`
- Password: `demo`

## Installation og kørsel lokalt
Hvis du ønsker at køre applikationen lokalt skal du:

1. Sørg for at have Java 21 og Maven installeret
2. Klon projektet fra GitHub: [github.com/AdamLevy2001/Projekt3_Gruppe_4.git](https://github.com/AdamLevy2001/Projekt3_Gruppe_4.git)
3. Opret en lokal MySQL-database
4. Kør SQL-scripts i denne rækkefølge:
   - `src/main/resources/databaseScript.sql`
   - `src/main/resources/databaseTestDataScript.sql`
5. Tilføj dine egne databaseoplysninger som miljøvariabler:
   - `JDBC_DATABASE_URL`
   - `JDBC_USERNAME`
   - `JDBC_PASSWORD`
6. Åbn projektet i din IDE (fx IntelliJ IDEA) og kør 
   `Projekt3Gruppe4Application.java` filen

## Teknologier
- Java 21
- Spring Boot (se pom.xml for dependencies)
- MySQL
- Thymeleaf
- HTML og CSS
- Azure Web App (deployment)
- GitHub Actions (CI/CD)

## Projektgruppe
- Adam Jes Levy - AdamLevy2001
- Jama Mirdadi - jama2pac
- Marcus Fevre Wahlgren - MFWahlgren
- Mathias Boljanac Holst - maho0012
