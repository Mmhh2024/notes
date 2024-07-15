INSERT INTO notes (id,idutilisateur,  nom, contenu, description, dateAjout, dateModification) VALUES
    ( 1, 200, 'notes 1 de 1','contenu note une de un ', 'description 1 de 1', '2024-04-28', '2024-04-28'),
    ( 2, 200, 'notes 2 de 1','contenu note deux de un ', 'description 2 de 1', '2024-06-05', '2024-06-07'),
    (3, 200, 'notes 3 de 1','contenu note deuroisx de un ', 'description 3 de 1', '2024-06-06', '2024-06-07'),
    ( 4, 300, 'notes 1 de 2','contenu note une de deux ', 'description 1 de 2', '2024-06-05', '2024-06-05') ;



post<T>(url: string, 

body: any, options: { headers?: HttpHeaders | { [header: string]: string | string[]; } | undefined; 
observe: "events"; 
context?: HttpContext | undefined; 
params?: HttpParams | { ...; } | undefined;
 reportProgress?: boolean | undefined;

 responseType?: "json" | undefined;
 withCredentials?: boolean | undefined;
 transferCache?: boolean | ... 1 more ... | undefined; }): Observable<HttpEvent<T>

>

Constructs a POST request that interprets the body as JSON and returns the full event stream.