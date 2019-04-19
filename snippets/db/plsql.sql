----------PROCEDURE 
'gfgargaer
jvaergh'
CREATE OR REPLACE PACKAGE pakiet1 AS
  PROCEDURE dodaj(v_isbn     t_ksiazka.isbn%TYPE,
                  v_tytul    T_KSIAZKA.tytul%TYPE,
                  v_wydawca  T_KSIAZKA.wydawca%TYPE,
                  v_rok      T_KSIAZKA.rok%TYPE,
                  v_oprawa   T_KSIAZKA.oprawa%TYPE,
                  v_dostawca T_KSIAZKA.dostawca%TYPE,
                  v_ilosc    T_KSIAZKA.ilosc%TYPE,
                  v_cena     T_KSIAZKA.cena%TYPE);
  PROCEDURE usun (v_isbn T_KSIAZKA.isbn%TYPE);
END ;
 
CREATE OR REPLACE PACKAGE BODY pakiet1 AS
  wyjatek EXCEPTION ;
  PRAGMA EXCEPTION_INIT(wyjatek,-2292);
  PROCEDURE dodaj(v_isbn     t_ksiazka.isbn%TYPE,
                  v_tytul    T_KSIAZKA.tytul%TYPE,
                  v_wydawca  T_KSIAZKA.wydawca%TYPE,
                  v_rok      T_KSIAZKA.rok%TYPE,
                  v_oprawa   T_KSIAZKA.oprawa%TYPE,
                  v_dostawca T_KSIAZKA.dostawca%TYPE,
                  v_ilosc    T_KSIAZKA.ilosc%TYPE,
                  v_cena     T_KSIAZKA.cena%TYPE) IS
    BEGIN
      INSERT INTO T_KSIAZKA VALUES (v_isbn,v_tytul,v_wydawca,v_rok,v_oprawa,v_dostawca,v_ilosc,v_cena);
      EXCEPTION WHEN DUP_VAL_ON_INDEX THEN DBMS_OUTPUT.put_line(SQLERRM || ' Naruszenie unikalnoœci klucza g³ównego!');
      WHEN INVALID_NUMBER THEN DBMS_OUTPUT.put_line(SQLERRM || ' Niepoprawne wartoœci!');
      WHEN OTHERS  THEN DBMS_OUTPUT.put_line(SQLERRM);
    END;
  PROCEDURE usun(v_isbn T_KSIAZKA.isbn%TYPE) IS
    BEGIN
      DELETE FROM T_KSIAZKA WHERE T_KSIAZKA.ISBN = v_isbn;
      EXCEPTION WHEN wyjatek THEN
      DBMS_OUTPUT.put_line('Usuwanie niemo¿liwe, po³¹czenie z inn¹ tabel¹!');
    END;
END ;
 
 
SELECT *
FROM T_KSIAZKA;
 
BEGIN
  pakiet1.dodaj(1,'dal',1,78,'miekka',3,23,45);
END;
 
 
BEGIN
  pakiet1.dodaj('674','dal','3','78','45','1','13','45');
END;
 
SELECT *
FROM T_KSIAZKA;
 
BEGIN
  pakiet1.usun(674);
END;

----------------------------------------SEQUENCE

CREATE TABLE t_sek(
  kolumna1 NUMBER,
  kolumna2 NUMBER,
  CONSTRAINT kglownysek PRIMARY KEY (kolumna2,kolumna1)
);
 
 
DROP SEQUENCE  sekw1;
DROP SEQUENCE sekw2;
 
CREATE SEQUENCE sekw1
  INCREMENT BY 1
  START WITH 1;
 
CREATE SEQUENCE sekw2
  INCREMENT BY 1
  START WITH 1
  MAXVALUE 2
  NOCACHE
  CYCLE ;
 
------
BEGIN
  FOR i IN 1..3 LOOP
    IF (i=1 OR i=2 OR i=3) THEN
    --INSERT INTO t_sek(kolumna1,kolumna2) VALUES (sekw1.currval,sekw2.currval);
    --INSERT INTO t_sek(kolumna1,kolumna2) VALUES (sekw1.currval,sekw2.nextval);
      DBMS_OUTPUT.put_line(sekw1.NEXTVAL||sekw2.NEXTVAL);
      DBMS_OUTPUT.put_line(sekw1.CURRVAL||sekw2.NEXTVAL);
      END IF ;
  END LOOP;
END;
 
SELECT sekw1.NEXTVAL FROM dual;
DROP TABLE t_sek;
DELETE FROM t_sek;
 
SELECT *
FROM t_sek;

-------------------------FUNCTION
CREATE OR REPLACE FUNCTION func
  RETURN T_KLIENT.id_klienta%TYPE
  IS
  v_p T_KLIENT.id_klienta%TYPE;
  BEGIN
    SELECT ID_KLIENTA INTO v_p FROM T_ZAMOWIENIE  GROUP BY ID_KLIENTA HAVING COUNT(ID_KLIENTA)=1;
    RETURN v_p;
    EXCEPTION
    WHEN NO_DATA_FOUND THEN DBMS_OUTPUT.put_line(SQLERRM); RETURN -2;
    WHEN TOO_MANY_ROWS THEN DBMS_OUTPUT.put_line(SQLERRM); RETURN -1;
    WHEN OTHERS THEN DBMS_OUTPUT.put_line(SQLERRM); RETURN -3;
  END;
 
 
SELECT *
FROM komunikat;
 
 
BEGIN
  DBMS_OUTPUT.put_line(func());
END;