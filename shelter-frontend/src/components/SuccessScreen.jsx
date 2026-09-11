import React from 'react';

const SuccessScreen = ({ onReturn }) => {
  return (
    <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', flex: 1 }}>
      <div style={{ width: '540px', border: '1px solid #e5e7eb', borderRadius: '12px', overflow: 'hidden', boxShadow: '0 10px 15px -3px rgba(0, 0, 0, 0.05)' }}>
        <div style={{ backgroundColor: '#111827', color: '#fff', padding: '16px', fontWeight: '600', textAlign: 'center', fontSize: '15px' }}>System Schroniska - Sukces</div>
        <div style={{ padding: '40px 32px', backgroundColor: '#fff', textAlign: 'center' }}>
          <div style={{ fontSize: '28px', color: '#15803d', marginBottom: '24px', backgroundColor: '#dcfce7', borderRadius: '50%', width: '64px', height: '64px', display: 'flex', alignItems: 'center', justifyContent: 'center', margin: '0 auto 24px auto', fontWeight: 'bold' }}>✓</div>
          <h3 style={{ margin: '0 0 12px 0', fontSize: '20px', fontWeight: '700', color: '#111827', letterSpacing: '-0.5px' }}>Wniosek został pomyślnie zaakceptowany!</h3>
          <p style={{ color: '#4b5563', fontSize: '14px', margin: '6px 0', fontWeight: '500' }}> Status wniosku w bazie danych został zmieniony na ZAAKCEPTOWANY.</p>
          <p style={{ color: '#6b7280', fontSize: '14px', margin: '6px 0 36px 0' }}>Dostępność zwierzęcia została zaktualizowana.</p>
          <button
            onClick={onReturn}
            style={{ backgroundColor: '#4b5563', color: '#fff', padding: '12px 28px', border: 'none', borderRadius: '8px', cursor: 'pointer', fontWeight: '600', fontSize: '14px', boxShadow: '0 1px 2px rgba(0,0,0,0.05)' }}
          >
            Powrót do listy wniosków
          </button>
        </div>
      </div>
    </div>
  );
};

export default SuccessScreen;