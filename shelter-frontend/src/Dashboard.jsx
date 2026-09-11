import React, { useState, useEffect } from 'react';
import ApplicationList from './components/ApplicationList';
import SystemRejection from './components/SystemRejection';
import ManualVerification from './components/ManualVerification';
import ClientForm from './components/ClientForm';
import SuccessScreen from './components/SuccessScreen';
import RejectionScreen from './components/RejectionScreen';

const Dashboard = () => {
  const [applications, setApplications] = useState([]);
  const [selectedApp, setSelectedApp] = useState(null);
  const [currentView, setCurrentView] = useState('LIST');
  const [employeeId] = useState(1);

  const [chkFormal, setChkFormal] = useState(false);
  const [chkManual, setChkManual] = useState(false);

  const [clientMetraz, setClientMetraz] = useState('');
  const [clientZwierzeta, setClientZwierzeta] = useState(false);

  const [error, setError] = useState(null);
  const connectionErrorMessage = 'Nie udało się połączyć z serwerem. Spróbuj ponownie.';

  useEffect(() => {
    fetchApplications();
  }, []);

  const fetchApplications = () => {
    fetch('http://localhost:8080/api/applications')
      .then(res => res.json())
      .then(data => {
        setApplications(data);
        setError(null);
      })
      .catch(err => {
        console.error(err);
        setError(connectionErrorMessage);
      });
  };

  const handleProcessClick = () => {
    if (!selectedApp) return;

    fetch(`http://localhost:8080/api/applications/${selectedApp.id}/select?employeeId=${employeeId}`, { method: 'POST' })
      .then(res => res.json())
      .then(updatedApp => {
        setSelectedApp(updatedApp);
        fetchApplications();
        setError(null);

        if (updatedApp.status === "REJECTED_BY_SYSTEM") {
          setCurrentView('SYSTEM_REJECTION');
        } else {
          setClientMetraz(updatedApp.apartmentSize);
          setClientZwierzeta(updatedApp.hasOtherAnimals);
          setChkFormal(false);
          setChkManual(false);
          setCurrentView('MANUAL_VERIFY');
        }
      })
      .catch(err => {
        console.error(err);
        setError(connectionErrorMessage);
      });
  };

  const handleConfirmSystemRejection = () => {
    fetchApplications();
    setCurrentView('LIST');
    setSelectedApp(null);
  };

  const handleAcceptApplication = () => {
    fetch(`http://localhost:8080/api/applications/${selectedApp.id}/accept`, { method: 'POST' })
      .then(() => {
        fetchApplications();
        setError(null);
        setCurrentView('SUCCESS');
      })
      .catch(err => {
        console.error(err);
        setError(connectionErrorMessage);
      });
  };

  const handleRejectApplication = () => {
    fetch(`http://localhost:8080/api/applications/${selectedApp.id}/reject`, { method: 'POST' })
      .then(() => {
        fetchApplications();
        setError(null);
        setCurrentView('MANUAL_REJECTION');
      })
      .catch(err => {
        console.error(err);
        setError(connectionErrorMessage);
      });
  };

  const handleGoToClient = () => {
    fetch(`http://localhost:8080/api/applications/${selectedApp.id}/flag-incomplete`, { method: 'POST' })
      .then(res => res.json())
      .then(updatedApp => {
        setSelectedApp(updatedApp);
        fetchApplications();
        setError(null);
        setCurrentView('CLIENT_UPDATE');
      })
      .catch(err => {
        console.error(err);
        setError(connectionErrorMessage);
      });
  };

  const handleClientSubmitUpdate = () => {
    const apartmentSize = clientMetraz === '' ? 0 : clientMetraz;
    fetch(`http://localhost:8080/api/applications/${selectedApp.id}/client-update?apartmentSize=${apartmentSize}&hasOtherAnimals=${clientZwierzeta}`, {
      method: 'PUT'
    })
      .then(() => {
        fetchApplications();
        setError(null);
        setCurrentView('LIST');
        setSelectedApp(null);
      })
      .catch(err => {
        console.error(err);
        setError(connectionErrorMessage);
      });
  };

  return (
    <div style={{ fontFamily: '"Inter", "Segoe UI", sans-serif', backgroundColor: '#f3f4f6', minHeight: '100vh', padding: '0', color: '#1f2937', width: '100vw', boxSizing: 'border-box', display: 'flex', flexDirection: 'column' }}>

      <div style={{ backgroundColor: '#111827', color: '#ffffff', padding: '16px 32px', display: 'flex', justifyContent: 'space-between', alignItems: 'center', boxShadow: '0 4px 6px -1px rgba(0,0,0,0.1)' }}>
        <span style={{ fontSize: '20px', fontWeight: '700', letterSpacing: '-0.5px' }}>
          {currentView === 'CLIENT_UPDATE' ? 'System Schroniska - Uzupełnianie braków' : currentView === 'MANUAL_VERIFY' ? 'System Schroniska - Weryfikacja Wniosku' : 'System Schroniska - Panel Pracownika'}
        </span>
      </div>

      <div style={{ display: 'flex', gap: '32px', padding: '32px', flex: 1, maxWidth: '1400px', width: '100%', margin: '0 auto', boxSizing: 'border-box' }}>

        <div style={{ width: '240px', backgroundColor: '#ffffff', padding: '16px', borderRadius: '12px', height: 'fit-content', boxShadow: '0 1px 3px rgba(0,0,0,0.05)', display: 'flex', flexDirection: 'column', gap: '8px' }}>
          <div style={{ padding: '12px 16px', borderRadius: '8px', backgroundColor: currentView === 'LIST' ? '#f3f4f6' : 'transparent', fontWeight: '600', border: '1px solid ' + (currentView === 'LIST' ? '#e5e7eb' : 'transparent'), color: currentView === 'LIST' ? '#111827' : '#6b7280' }}>Wnioski</div>
          <div style={{ padding: '12px 16px', borderRadius: '8px', backgroundColor: currentView === 'MANUAL_VERIFY' ? '#f3f4f6' : 'transparent', fontWeight: '500', border: '1px solid ' + (currentView === 'MANUAL_VERIFY' ? '#e5e7eb' : 'transparent'), color: currentView === 'MANUAL_VERIFY' ? '#111827' : '#9ca3af' }}>Weryfikacja</div>
          <div style={{ padding: '12px 16px', borderRadius: '8px', backgroundColor: currentView === 'CLIENT_UPDATE' ? '#f3f4f6' : 'transparent', fontWeight: '500', border: '1px solid ' + (currentView === 'CLIENT_UPDATE' ? '#e5e7eb' : 'transparent'), color: currentView === 'CLIENT_UPDATE' ? '#111827' : '#9ca3af' }}>Uzupełnianie</div>
          <div style={{ padding: '12px 16px', borderRadius: '8px', backgroundColor: (currentView === 'SUCCESS' || currentView === 'MANUAL_REJECTION') ? '#f3f4f6' : 'transparent', fontWeight: '500', border: '1px solid ' + ((currentView === 'SUCCESS' || currentView === 'MANUAL_REJECTION') ? '#e5e7eb' : 'transparent'), color: (currentView === 'SUCCESS' || currentView === 'MANUAL_REJECTION') ? '#111827' : '#9ca3af' }}>Potwierdzenie</div>
        </div>

        <div style={{ flex: 1, backgroundColor: '#ffffff', padding: '32px', borderRadius: '12px', boxShadow: '0 1px 3px rgba(0,0,0,0.05)', minHeight: '520px', display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>

          {error && (
            <div style={{ backgroundColor: '#fef2f2', color: '#991b1b', padding: '14px 18px', borderRadius: '8px', marginBottom: '20px', fontSize: '14px', borderLeft: '4px solid #ef4444', fontWeight: '500', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
              <span>{error}</span>
              <button onClick={() => setError(null)} style={{ background: 'none', border: 'none', color: '#991b1b', cursor: 'pointer', fontWeight: '700', fontSize: '16px', lineHeight: 1 }}>×</button>
            </div>
          )}

          {currentView === 'LIST' && (
            <ApplicationList
              applications={applications}
              selectedApp={selectedApp}
              onSelectApp={setSelectedApp}
              onProcess={handleProcessClick}
            />
          )}

          {currentView === 'SYSTEM_REJECTION' && (
            <SystemRejection onConfirm={handleConfirmSystemRejection} />
          )}

          {currentView === 'MANUAL_VERIFY' && selectedApp && (
            <ManualVerification
              selectedApp={selectedApp}
              chkFormal={chkFormal}
              setChkFormal={setChkFormal}
              chkManual={chkManual}
              setChkManual={setChkManual}
              onBack={() => setCurrentView('LIST')}
              onReject={handleRejectApplication}
              onAccept={handleAcceptApplication}
              onGoToClient={handleGoToClient}
            />
          )}

          {currentView === 'CLIENT_UPDATE' && (
            <ClientForm
              clientMetraz={clientMetraz}
              setClientMetraz={setClientMetraz}
              clientZwierzeta={clientZwierzeta}
              setClientZwierzeta={setClientZwierzeta}
              onSubmit={handleClientSubmitUpdate}
            />
          )}

          {currentView === 'SUCCESS' && (
            <SuccessScreen onReturn={() => { setCurrentView('LIST'); setSelectedApp(null); }} />
          )}

          {currentView === 'MANUAL_REJECTION' && (
            <RejectionScreen onReturn={() => { setCurrentView('LIST'); setSelectedApp(null); }} />
          )}

        </div>
      </div>
    </div>
  );
};

export default Dashboard;