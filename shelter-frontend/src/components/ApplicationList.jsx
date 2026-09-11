import React from 'react';

const ApplicationList = ({ applications, selectedApp, onSelectApp, onProcess }) => {
  return (
    <div style={{ display: 'flex', flexDirection: 'column', height: '100%', justifyContent: 'space-between', flex: 1 }}>
      <div>
        <h3 style={{ backgroundColor: '#374151', color: '#fff', padding: '14px 20px', margin: '0 0 8px 0', fontSize: '16px', fontWeight: '600', borderRadius: '8px' }}>lista Wniosków (Ekran Główny Pracownika)</h3>
        <p style={{ fontSize: '13px', color: '#6b7280', margin: '0 0 20px 4px', fontWeight: '500' }}>ID Wniosku | Imię Zwierzęcia | Imię i Nazwisko Klienta | Status</p>

        <div style={{ border: '1px solid #e5e7eb', borderRadius: '8px', overflow: 'hidden' }}>
          <table style={{ width: '100%', borderCollapse: 'collapse', textAlign: 'left', fontSize: '14px' }}>
            <thead>
              <tr style={{ backgroundColor: '#f9fafb', borderBottom: '1px solid #e5e7eb' }}>
                <th style={{ padding: '14px 16px', color: '#4b5563', fontWeight: '600' }}>ID Wniosku</th>
                <th style={{ padding: '14px 16px', color: '#4b5563', fontWeight: '600' }}>Imię Zwierzęcia</th>
                <th style={{ padding: '14px 16px', color: '#4b5563', fontWeight: '600' }}>Imię i Nazwisko Klienta</th>
                <th style={{ padding: '14px 16px', color: '#4b5563', fontWeight: '600' }}>Status</th>
              </tr>
            </thead>
            <tbody>
              {applications.map((app) => (
                <tr
                  key={app.id}
                  onClick={() => onSelectApp(app)}
                  style={{
                    borderBottom: '1px solid #f3f4f6',
                    cursor: 'pointer',
                    backgroundColor: selectedApp?.id === app.id ? '#f0fdf4' : 'transparent'
                  }}
                >
                  <td style={{ padding: '14px 16px', fontWeight: '700', color: selectedApp?.id === app.id ? '#166534' : '#111827' }}>{app.id}</td>
                  <td style={{ padding: '14px 16px', color: '#374151' }}>{app.animalName}</td>
                  <td style={{ padding: '14px 16px', color: '#374151' }}>{app.clientFullName}</td>
                  <td style={{ padding: '14px 16px' }}>
                    <span style={{
                      border: '1px solid #d1d5db', padding: '4px 12px', borderRadius: '6px', backgroundColor: '#fff', fontSize: '12px', fontWeight: '600', display: 'inline-block',
                      borderColor: app.status === 'ACCEPTED' ? '#bbf7d0' : (app.status === 'REJECTED' || app.status === 'REJECTED_BY_SYSTEM') ? '#fecaca' : '#d1d5db',
                      color: app.status === 'ACCEPTED' ? '#166534' : (app.status === 'REJECTED' || app.status === 'REJECTED_BY_SYSTEM') ? '#991b1b' : '#374151'
                    }}>
                      {app.status === 'NEW' ? 'Nowy' :
                       app.status === 'IN_PROGRESS' ? 'W trakcie realizacji' :
                       app.status === 'ACCEPTED' ? 'ZAAKCEPTOWANY' :
                       app.status === 'REJECTED' ? 'ODRZUCONY' :
                       app.status === 'REJECTED_BY_SYSTEM' ? 'ODRZUCONY PRZEZ SYSTEM' : app.status}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      <div style={{ display: 'flex', justifyContent: 'flex-end', marginTop: '32px' }}>
        <button
          onClick={onProcess}
          disabled={!selectedApp || selectedApp.status === 'ACCEPTED' || selectedApp.status === 'REJECTED' || selectedApp.status === 'REJECTED_BY_SYSTEM'}
          style={{
            backgroundColor: (!selectedApp || selectedApp.status === 'ACCEPTED' || selectedApp.status === 'REJECTED' || selectedApp.status === 'REJECTED_BY_SYSTEM') ? '#e5e7eb' : '#4b5563',
            color: (!selectedApp || selectedApp.status === 'ACCEPTED' || selectedApp.status === 'REJECTED' || selectedApp.status === 'REJECTED_BY_SYSTEM') ? '#9ca3af' : '#fff',
            padding: '12px 28px', border: 'none', borderRadius: '8px',
            cursor: (!selectedApp || selectedApp.status === 'ACCEPTED' || selectedApp.status === 'REJECTED' || selectedApp.status === 'REJECTED_BY_SYSTEM') ? 'not-allowed' : 'pointer',
            fontSize: '14px', fontWeight: '600'
          }}
        >
          Procesuj wniosek
        </button>
      </div>
    </div>
  );
};

export default ApplicationList;