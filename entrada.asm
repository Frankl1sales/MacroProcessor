MACRO1  MACRO  PARA1, PARA2
        LDA PARA1
        ADD PARA2
        STA RESULT
        MEND

MACRO2  MACRO  PARA
        LDCH PARA
        STA CHAR
        MEND

START   START 0
        MACRO1 ARG1, ARG2
        JSUB SUBROUTINE
        MACRO2 ARG3
        LDX #10
        LOOP    TD INPUT
                JEQ ENDLOOP
                RD REC
                JSUB PROCESS_RECORD
                J LOOP
        ENDLOOP NOOP
        ... ; Mais instruções
        J EXIT
        SUBROUTINE SUBR
                ... ; Corpo da sub-rotina
                RSUB

INPUT   BYTE    X'F0'
REC     RESW    1
RESULT  RESW    1
CHAR    RESB    1

EXIT    TRAP    x25
