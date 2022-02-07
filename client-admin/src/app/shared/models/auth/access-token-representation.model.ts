
export class AccessTokenRepresentation {
  public access_token: string;
  public expires_in: number;
  public expires_in_date: Date;
  public refresh_expires_in: number;
  public refresh_expires_in_date: Date;
  public refresh_token: string;
  public token_type: string;
  public not_before_policy: number;
  public session_state: string;
  public issued_at: number;
  public scope: string;
}
