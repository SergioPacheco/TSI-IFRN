unit Sair_u;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, Buttons, jpeg, ExtCtrls, db;

type
  TFSair = class(TForm)
    Panel1: TPanel;
    Label2: TLabel;
    Panel2: TPanel;
    Label1: TLabel;
    Image1: TImage;
    Panel3: TPanel;
    BitBtn1: TBitBtn;
    BitBtn2: TBitBtn;
    procedure BitBtn1Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FSair: TFSair;

implementation

uses UDM_u;

{$R *.dfm}

procedure TFSair.BitBtn1Click(Sender: TObject);
begin
  Application.Terminate;
end;

procedure TFSair.FormClose(Sender: TObject; var Action: TCloseAction);
begin
  Action := cafree;
end;

end.
